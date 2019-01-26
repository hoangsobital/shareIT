package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.News;
import model.dao.NewsDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminSearchNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NewsDAO newsDAO;
    public AdminSearchNewsController() {
        super();
        newsDAO = new NewsDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//check login
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		String search = request.getParameter("timkiem");
		System.out.println(search);
		
		int sumNews = newsDAO.countNews(search);
		int sumPage = (int)Math.ceil((float)sumNews/DefineUtil.ROW_COUNT_ADMIN);
		System.out.println(sumPage);

		int current_page = 1;
		if(request.getParameter("page") != null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		if(current_page > sumPage){
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=4");
			return;
		}
		int offset = (current_page - 1) * DefineUtil.ROW_COUNT_ADMIN;
		
		request.setAttribute("sumPage", sumPage);
		request.setAttribute("current_page", current_page);
		
		ArrayList<News> news = newsDAO.getItemsPaginationS(search,offset); 
		if(news != null && news.size() > 0){
			request.setAttribute("news", news);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/news/search.jsp");
			rd.forward(request, response);
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		}
	}

}
