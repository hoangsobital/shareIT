package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import model.bean.News;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminIndexNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
    public AdminIndexNewsController() {
        super();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check login
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		int sumNews = newsDAO.countNews();
		int sumPage = (int)Math.ceil((float)sumNews/DefineUtil.ROW_COUNT_ADMIN);

		int current_page = 1;
		if(request.getParameter("page") != null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		if(current_page > sumPage){
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		int offset = (current_page - 1) * DefineUtil.ROW_COUNT_ADMIN;
		
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("current_page", current_page);
		
		ArrayList<News> news = newsDAO.getItemsPagination(offset); 
		request.setAttribute("news", news);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/news/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
