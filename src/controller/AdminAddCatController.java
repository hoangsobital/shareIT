package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import util.AuthUtil;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;

	public AdminAddCatController() {
		super();
		catDAO = new CategoryDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check login
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		request.setAttribute("categories", catDAO.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check login
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		int parent_id = 0;
		try {
			parent_id = Integer.parseInt(request.getParameter("parent_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
		// kiểm tra rỗng
		if ("".equals(name)) {
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		// kiểm tra trùng tên danh mục
		if (catDAO.checkAddCat(name) != null) {
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp?msg=1");
			rd.forward(request, response);
			return;
		}
		Category category = new Category(0, name, parent_id);
		if (catDAO.addItem(category) > 0) {
			// thành công
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=1");
			return;
		} else {
			// thất bại
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp?msg=0");
			rd.forward(request, response);
			return;
		}

	}

}
