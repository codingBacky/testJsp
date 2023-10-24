package com.backy.controller.action;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backy.controller.action.Action;
import com.backy.dao.ShopDAO;

public class ShopInputFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		ShopDAO sDao = ShopDAO.getInstance();
		int custno = sDao.getSelectCustno();
		
		request.setAttribute("custno", custno+1);
		System.out.println("custno : " + custno);
		
		RequestDispatcher dis = request.getRequestDispatcher("shop/input.jsp");
		dis.forward(request, response);
	}

}
