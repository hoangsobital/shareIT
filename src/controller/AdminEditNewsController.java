package controller;

import java.io.File;
import java.io.IOException;

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
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
	private NewsDAO newsDAO;
    public AdminEditNewsController() {
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		}
		News news = newsDAO.getItem(id);
		request.setAttribute("news", news);
		request.setAttribute("categories", catDAO.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//check login
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		}
		//kiểm tra dữ liệu cũ != null
			News oldNew = newsDAO.getItem(id);
			if(oldNew == null){
				response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
				return;
			}
		
		String name = request.getParameter("name");
		int cat_id = 0;
		int is_slide = 0;
		try {
			cat_id = Integer.parseInt(request.getParameter("category"));
			//System.out.println(cat_id);
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		}
		
		String slide = request.getParameter("is_slide");
		//System.out.println(slide);
		if(slide != null){
			is_slide = 1;
		}
		//System.out.println(is_slide);
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		//kiểm tra rỗng
		if("".equals(name) || "".equals(preview) || "".equals(detail)){
			request.setAttribute("news", newsDAO.getItem(id));
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?msg=0");
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
		
		String oldPic = oldNew.getPicture();
		String delPic = request.getParameter("delete_picture");
		
		oldNew.setName(name);
		oldNew.setPreview(preview);
		oldNew.setDetail(detail);
		oldNew.setIs_slide(is_slide);
		oldNew.setCategory(new Category(cat_id, null, 0));
		if(!picture.isEmpty() || delPic != null){
			oldNew.setPicture(picture);
		}
		
		if(newsDAO.editItem(oldNew) > 0){
			//thành công
			if(!picture.isEmpty() || delPic != null){
				//xóa file
				String oldFilePath = path + File.separator + oldPic;
				File delFile = new File(oldFilePath);
				if(delFile.exists()){
					delFile.delete();
				}
				if(!picture.isEmpty()){
					//ghi file
					String filePath = path + File.separator + picture;
					file.write(filePath);
				}
			}
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
			return;
		}else{
			//thất bại
			request.setAttribute("news", newsDAO.getItem(id));
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/edit.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		
	}

}
