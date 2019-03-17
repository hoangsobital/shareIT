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

import model.bean.Advertise;
import model.dao.AdvertiseDAO;
import util.AuthUtil;
import util.FileUtil;
@MultipartConfig
public class AdminAddAdvController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdvertiseDAO advDAO;
    public AdminAddAdvController() {
        super();
        advDAO = new AdvertiseDAO();
        //Hello
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check login
			if(!AuthUtil.checkLogin(request, response)){
				response.sendRedirect(request.getContextPath()+"/auth/login");
				return;
			}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/adv/add.jsp");
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
		String link = request.getParameter("link");
		Part file = request.getPart("picture");
		String filename = FileUtil.getName(file);
		//System.out.println(filename);
		
		//kiểm tra rỗng
		if("".equals(name) || "".equals(link) || "".equals(filename)){
			RequestDispatcher rd = request.getRequestDispatcher("/admin/adv/add.jsp?msg=0");
			rd.forward(request, response);
			return;
			
		}
		//đường dẫn lưu file
		final String path = request.getServletContext().getRealPath("/files-adv");
		System.out.println(path);
		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir(); 
		}
		String picture = FileUtil.rename(filename);
		String filepath = path + File.separator + picture;
		//kiểm tra trùng quảng cáo
		/*if(advDAO.checkAddAdv(name,link,picture) != null){
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin/adv/add.jsp?msg=1");
			rd.forward(request, response);
			return;
			
		}*/
		Advertise advertise = new Advertise(0, filename, picture, link);
		if(advDAO.addItem(advertise) > 0){
			//thành công
			//ghi file
			if(!picture.isEmpty()){
				file.write(filepath);
			}
			response.sendRedirect(request.getContextPath() + "/admin/advs?msg=1");
			return;
		}else{
			//thất bại
			RequestDispatcher rd = request.getRequestDispatcher("/admin/adv/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		
	}

}
