package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bitacademy.mysite.vo.Uservo;

public class UserDao {
	

	public Uservo findByEmailAndPassword(Uservo vo) throws SQLException{
		
		Uservo userVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = getConnection();
		
		String sql="select no,name from user where email=? and password=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getEmail());
		pstmt.setString(2, vo.getPassword());

		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			Long no = rs.getLong(1);
			String name = rs.getString(2);
			
			userVo = new Uservo();
			userVo.setNo(no);
			userVo.setName(name);
			
		}
		}catch(SQLException e) {
			
			System.out.println("error"+e);
		}finally {
			if(rs!=null) {
				
				rs.close();
				
			}
			if(pstmt!=null) {
				
				pstmt.close();
			}
			
			if(conn!=null) {
				
				conn.close();
			}
		}
		

		return userVo;
		
		
	}
	
	
	public boolean insert(Uservo vo) throws SQLException {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
		conn = getConnection();
		
		String sql="insert into user values(null,?,?,?,?,now());";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getEmail());
		pstmt.setString(3, vo.getPassword());
		pstmt.setString(4, vo.getGender());
		
		int count = pstmt.executeUpdate();
		
		result = count == 1;
		
		}catch(SQLException e) {
			
			System.out.println("error"+e);
		}finally {
			if(pstmt!=null) {
				
				pstmt.close();
			}
			
			if(conn!=null) {
				
				conn.close();
			}
		}
		return result;
	}
	
	
public Uservo findByNo(Long no) throws SQLException{
		
		Uservo userVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		conn = getConnection();
		
		String sql="select name,email,gender from user where no=?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, no);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			String name = rs.getString(1);
			String email = rs.getString(2);
			String gender = rs.getString(3);
			
			userVo = new Uservo();
			userVo.setName(name);
			userVo.setEmail(email);
			userVo.setGender(gender);
			
		}
		}catch(SQLException e) {
			
			System.out.println("error"+e);
		}finally {
			if(rs!=null) {
				
				rs.close();
				
			}
			if(pstmt!=null) {
				
				pstmt.close();
			}
			
			if(conn!=null) {
				
				conn.close();
			}
		}
		

		return userVo;
		
		
	}


//---------------------- 회원정보수정-----------------------
public boolean Update(Uservo vo) {

	boolean result =false;
	Connection conn = null;
	PreparedStatement pstmt =null;

	try {
		
		conn = getConnection();

		//3. SQL 준비 
		String sql = "update user set name=?,password=?,gender=? where email=?";
		pstmt = conn.prepareStatement(sql);

		//4. 바인딩
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
        pstmt.setString(3, vo.getGender());
        pstmt.setString(4, vo.getEmail());

		
		//5. SQL문 실행
		int count = pstmt.executeUpdate();
		
		//6. 결과
		result = count == 1;


	}catch(SQLException e) {

		System.out.println("error"+e);
	}finally {

		try {

			if(pstmt!=null) {

				pstmt.close();

			}
			if(conn!=null) {
				conn.close();}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	return result;
}
	
	
	
	
	private Connection getConnection() throws SQLException{

		Connection conn = null;

		try {
			//1. JDBC Driver 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			//2. 연결
			String url ="jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb","webdb");
		}catch(ClassNotFoundException e) {

			System.out.println("error-"+e);
			
		}

		return conn;

	}
}