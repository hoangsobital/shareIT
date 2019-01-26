package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.dao.NewsDAO;
import util.AuthUtil;

public class AdminDelNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
    public AdminDelNewsController() {
        super();
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
		
		//dữ liệu cũ
		News oldNews = newsDAO.getItem(id);
		
		if(newsDAO.delItem(id) > 0){
			//thành công
			//xóa file
			String picture = oldNews.getPicture();
			String path = request.getServletContext().getRealPath("/files");
			System.out.println(path);
			
			String filepath = path + File.separator + picture;
			File delFile = new File(filepath);
			if(delFile.exists()){
				delFile.delete();
			}
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=3");
			return;
		}else{
			//thất bại
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
