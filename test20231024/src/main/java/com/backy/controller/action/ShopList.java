package com.backy.controller.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backy.dao.ShopDAO;
import com.backy.dto.MemberVO;

public class ShopList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
		ShopDAO sDao = ShopDAO.getInstance();
		
		List<MemberVO> lists = sDao.shopList();
		
		request.setAttribute("list", lists);
		RequestDispatcher dis = request.getRequestDispatcher("shop/list.jsp");
		dis.forward(request, response);
	}

}
