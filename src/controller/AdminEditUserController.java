package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.bean.UserRank;
import model.dao.UserDAO;
import model.dao.UserRankDAO;
import util.AuthUtil;
import util.StringUtil;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRankDAO urankDAO;
	private UserDAO userDAO;
    public AdminEditUserController() {
        super();
        urankDAO = new UserRankDAO();
        userDAO = new UserDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=0");
			return;
		}
		User user = userDAO.getItem(id);
		request.setAttribute("user", user);
		request.setAttribute("usersR", urankDAO.getItems());
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp");
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
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=0");
			return;
		}
		
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		int capbac = 0;
		try {
			capbac = Integer.parseInt(request.getParameter("capbac"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
		String email = request.getParameter("email");
		//kiểm tra rỗng
		if("".equals(username) || "".equals(fullname) || "".equals(email)){
			request.setAttribute("usersR", urankDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=0");
			rd.forward(request, response);
			return;
			
		}
		if("".equals(password)){
			password = userDAO.getItem(id).getPassword();
		}else{
			password = StringUtil.md5(password);
		}
		//kiểm tra trùng tên danh mục
		if(userDAO.checkEditUser(username, id) != null){
			request.setAttribute("usersR", urankDAO.getItems());
			request.setAttribute("user", userDAO.getItem(id));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		User user = new User(id, username, password, fullname, email,1, new UserRank(capbac, null));
		if(userDAO.editItem(user) > 0){
			//thành công
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=2");
			return;
		}else{
			//thất bại
			request.setAttribute("usersR", urankDAO.getItems());
			request.setAttribute("user", userDAO.getItem(id));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/edit.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
	}

}
