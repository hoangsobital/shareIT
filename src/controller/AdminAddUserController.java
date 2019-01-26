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

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserRankDAO urankDAO;
	private UserDAO userDAO;
    public AdminAddUserController() {
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
		request.setAttribute("usersR", urankDAO.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp");
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
		int active =1;
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		int capbac = 0;
		try {
			capbac = Integer.parseInt(request.getParameter("capbac"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=0");
			return;
		}
		String password =StringUtil.md5(request.getParameter("password"));
		String email = request.getParameter("email");
		
		//kiểm tra rỗng
		if("".equals(username) || "".equals(fullname) || "".equals(password) || "".equals(email)){
			request.setAttribute("usersR", urankDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=0");
			rd.forward(request, response);
			return;
			
		}
		//kiểm tra trùng tên đăng nhập
		if(userDAO.checkAddUser(username) != null){
			request.setAttribute("usersR", urankDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=1");
			rd.forward(request, response);
			return;
			
		}
		User user = new User(0, username, password, fullname, email,active, new UserRank(capbac, null));
		if(userDAO.addItem(user) > 0){
			//thành công
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=1");
			return;
		}else{
			//thất bại
			request.setAttribute("usersR", urankDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/user/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		
	}

}
