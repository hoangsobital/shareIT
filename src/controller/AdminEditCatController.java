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

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
    public AdminEditCatController() {
        super();
        catDAO = new CategoryDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
		Category cat = catDAO.getItem(id);
		request.setAttribute("cat", cat);
		request.setAttribute("categories", catDAO.getItems());
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
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
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
		
		String name = request.getParameter("name");
		int parent_id = 0;
		try {
			parent_id = Integer.parseInt(request.getParameter("parent_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
		System.out.println(parent_id);
		//kiểm tra rỗng
		if("".equals(name)){
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp?msg=0");
			rd.forward(request, response);
			return;
			
		}
		//kiểm tra trùng tên danh mục
		if(catDAO.checkEditCat(name, id) != null){
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp?msg=1");
			rd.forward(request, response);
			return;
		}
		Category category = new Category(id, name, parent_id);
		if(catDAO.editItem(category) > 0){
			//thành công
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=2");
			return;
		}else{
			//thất bại
			request.setAttribute("categories", catDAO.getItems());
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp?msg=0");
			rd.forward(request, response);
			return;
		}
		
	}

}
