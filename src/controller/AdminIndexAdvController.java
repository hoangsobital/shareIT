package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Advertise;
import model.dao.AdvertiseDAO;
import util.AuthUtil;

public class AdminIndexAdvController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdvertiseDAO advDAO;
    public AdminIndexAdvController() {
        super();
        advDAO = new AdvertiseDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//check login
		if(!AuthUtil.checkLogin(request, response)){
			response.sendRedirect(request.getContextPath()+"/auth/login");
			return;
		}
		
		ArrayList<Advertise> advertise = advDAO.getItems();
		request.setAttribute("advertise", advertise);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/adv/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
