package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    public AdminIndexUserController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check login
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		ArrayList<User> users = userDAO.getItems();
		request.setAttribute("users", users);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		int id = 0;
		int aactive = 0;
		int active = 0;
		try {
			id = Integer.parseInt(request.getParameter("aid"));
			aactive = Integer.parseInt(request.getParameter("aactive"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/admin/users?msg=1");
			return;
		}
		
		PrintWriter out = response.getWriter();
		if(aactive == 0){
			active=1;
			userDAO.changActive(active,id);
			out.println("<a href='javascript:void(0)' onclick='return changeActive("+id+","+active+")'><img src='/Java18-shareit/templates/admin/img/active.png' width='20px'/></a>");
		}else if(aactive == 1){
			active = 0;
			userDAO.changActive(active,id);
			out.println("<a href='javascript:void(0)' onclick='changeActive(" +id+ "," +active+ ")'><img src='/Java18-shareit/templates/admin/img/disactive.png' width='20px'/></a>");
			
		}
	}

}
