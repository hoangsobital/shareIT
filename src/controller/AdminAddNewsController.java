package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.FileUtil;
@MultipartConfig
public class AdminAddNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
	private NewsDAO newsDAO;
    public AdminAddNewsController() {
        super();
        catDAO = new CategoryDAO();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check login
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		request.setAttribute("categories", catDAO.getItems());
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check login
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int cat_id = 0;
		int is_slide = 0;
		try {
			cat_id = Integer.parseInt(request.getParameter("category"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
			return;
		}
		
		String slide = request.getParameter("is_slide");
		//System.out.println(slide);
		if(slide == null){
			is_slide = 0;
		}else{
			is_slide = 1;
		}
		//System.out.println(is_slide);
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		//kiểm tra rỗng
		if("".equals(name) || "".equals(preview) || "".equals(detail) || cat_id == 0){
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		
		Part file = request.getPart("picture");
		String picture = "";
		String filename = FileUtil.getName(file);
		//đường dẫn lưu file
			final String path = request.getServletContext().getRealPath("/files");
			System.out.println(path);
			File dirPath = new File(path);
			if (!dirPath.exists()) {
				dirPath.mkdir(); 
			}
		picture = FileUtil.rename(filename);
		String filepath = path + File.separator + picture;
		
		//ngày tạo từ hệ thông
		Date date = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_create = fm.format(date.getTime());
		int created_by = 1;
		
		News news = new News(0,name, preview, detail, date_create, created_by, picture, is_slide, new Category(cat_id, null, 0), null);
		if(newsDAO.addItem(news) > 0){
			//thành công
			if(!picture.isEmpty()){
				file.write(filepath);
			}
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
			return;
		}else{
			//thất bại
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		
	}

}
