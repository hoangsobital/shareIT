package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Advertise;
import model.dao.AdvertiseDAO;
import util.AuthUtil;

public class AdminDelAdvController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdvertiseDAO advDAO;
    public AdminDelAdvController() {
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
		
		//dữ liệu cũ
		Advertise oldAdv = advDAO.getItem(id);
		
		if(advDAO.delItem(id) > 0){
			//thành công
			//xóa file
			String picture = oldAdv.getPicture();
			String path = request.getServletContext().getRealPath("/files-adv");
			System.out.println(path);
			
			String filepath = path + File.separator + picture;
			File delFile = new File(filepath);
			if(delFile.exists()){
				delFile.delete();
			}
			response.sendRedirect(request.getContextPath() + "/admin/advs?msg=3");
			return;
		}else{
			//thất bại
			response.sendRedirect(request.getContextPath() + "/admin/adv"
					+ "s?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
