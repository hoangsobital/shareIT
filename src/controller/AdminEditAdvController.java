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
public class AdminEditAdvController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdvertiseDAO advDAO;
    public AdminEditAdvController() {
        super();
        advDAO = new AdvertiseDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/advs?msg=0");
			return;
		}
		Advertise advertise = advDAO.getItem(id);
		request.setAttribute("advertise", advertise);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/adv/edit.jsp");
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/advs?msg=0");
			return;
		}
		//lấy dữ liệu cũ
		Advertise oldAdv = advDAO.getItem(id);
		if(oldAdv == null){
			response.sendRedirect(request.getContextPath() + "/admin/advs?msg=0");
			return;
		}
		String name = request.getParameter("name");
		String link = request.getParameter("link");
		Part file = request.getPart("picture");
		String delPic = request.getParameter("delete_picture");
		String filename = FileUtil.getName(file);
		//System.out.println(filename);
		
		//kiểm tra rỗng
		if("".equals(name) || "".equals(link)){
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
		String oldPic = oldAdv.getPicture();
		oldAdv.setName(name);
		oldAdv.setLink(link);
		if(!picture.isEmpty() || delPic != null){
			oldAdv.setPicture(picture);
		}
		
		if(advDAO.editItem(oldAdv) > 0){
			//thành công
			if(!picture.isEmpty() || delPic != null){
				// xóa file
				String urlFile = path + File.separator + oldPic;
				File delFile = new File(urlFile);
				if(delFile.exists()){
					delFile.delete();
				}
				//ghi file
				if(!picture.isEmpty()){
					String filepath = path + File.separator + picture;
					file.write(filepath);
				}
			}
			response.sendRedirect(request.getContextPath() + "/admin/advs?msg=2");
			return;
		}else{
			//thất bại
			RequestDispatcher rd = request.getRequestDispatcher("/admin/adv/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		
	}

}
