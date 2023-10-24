package com.backy.controller.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backy.dao.ShopDAO;
import com.backy.dto.MemberVO;

public class ShopInputAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		MemberVO vo = new MemberVO();
		
		
		vo.setCustname(request.getParameter("custname"));
		vo.setPhone(request.getParameter("phone"));
		vo.setAddress(request.getParameter("address"));
		vo.setJoindate(request.getParameter("joindate"));
		vo.setGrade(request.getParameter("grade"));
		vo.setCity(request.getParameter("city"));
		
		System.out.println(vo);
		
		ShopDAO sDao = ShopDAO.getInstance();
		int result = sDao.insertShop(vo);
		
//		RequestDispatcher dis = request.getRequestDispatcher("shop/list.jsp");
//		dis.forward(request, response);
		response.sendRedirect("shop?command=shop_list");
	}

}
