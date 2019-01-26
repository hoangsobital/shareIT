package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;
import util.AuthUtil;

public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
	private NewsDAO newsDAO;
    public AdminDelCatController() {
        super();
        catDAO = new CategoryDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
		Category cat = catDAO.getItem(id);
		int parent_id = cat.getParent_id();
		//System.out.println(parent_id);
		ArrayList<Category> list_catC = catDAO.getItemsC(id);
		
		if(catDAO.delItem(id) > 0){
			if(parent_id == 0){
				//xóa danh mục con
				catDAO.delItemC(id);
				//xoas tin danh muc con
				for(int i = 0; i < list_catC.size(); i++){
					//System.out.println("Vvv"+list_catC.get(i).getId());
					newsDAO.delItemN(list_catC.get(i).getId());
				}
			}
			//xóa tin
			newsDAO.delItemN(id);
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=3");
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=0");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
