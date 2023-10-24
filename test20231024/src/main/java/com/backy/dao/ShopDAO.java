package com.backy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.backy.dto.MemberVO;
import com.backy.dto.ShopVO;

public class ShopDAO {
	private ShopDAO() {}
	private static ShopDAO instance = new ShopDAO();
	
	public static ShopDAO getInstance() {
		return instance;
	}
	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(Connection conn, PreparedStatement ps) {
		try {
			if(ps != null) ps.close();
			if(conn != null) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getSelectCustno() {
		int result = 0;
		String sql = "select custno from member_tbl_01 where rownum <=1 order by custno desc";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ShopDAO.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next())
				result= rs.getInt("custno");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ShopDAO.close(conn, ps, rs);
		}
		return result;
	}
	public int insertShop(MemberVO vo) {
		int result = 0;
		String sql = "insert into member_tbl_01 values(member_seq.nextval+7,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ShopDAO.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getCustname() );
			ps.setString(2, vo.getPhone() );
			ps.setString(3, vo.getAddress() );
			ps.setString(4, vo.getJoindate() );
			ps.setString(5, vo.getGrade() );
			ps.setString(6, vo.getCity() );
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ShopDAO.close(conn, ps);
		}
		return result;
	}
	public List<MemberVO> shopList() {
		
		String sql = "select * from member_tbl_01 order by custno desc";
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = ShopDAO.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo = new MemberVO();
				vo.setCustno(rs.getInt(1));
				vo.setCustname(rs.getString(2));
				vo.setPhone(rs.getString(3));
				vo.setAddress(rs.getString(4));
				vo.setJoindate(rs.getString(5));
				vo.setGrade(rs.getString(6));
				vo.setCity(rs.getString(7));
				System.out.println(vo);
				
				list.add(vo);
			}
	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ShopDAO.close(conn, ps, rs);
		}
		return list;
	}

	
}
