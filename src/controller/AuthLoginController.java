package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.StringUtil;

public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
    public AuthLoginController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/admin/news");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/auth/login.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//check login
		if(AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/admin/news");
			return;
		}
		//lấy dữ liệu
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//kiểm tra dữ liệu rỗng
		if("".equals(username) || "".equals(password)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		password = StringUtil.md5(password);
		//kiểm tra login
		User userLogin = userDAO.checkLogin(username,password);
		if(userLogin != null){
			//đăng nhập thành công
			session.setAttribute("userLogin", userLogin);
			response.sendRedirect(request.getContextPath()+"/admin/news");
			return;
		}else{
			//đăng nhập thất bại
			response.sendRedirect(request.getContextPath()+"/auth/login?msg=0");
			return;
		}
	}

}
