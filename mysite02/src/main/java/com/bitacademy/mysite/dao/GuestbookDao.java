package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.bitacademy.mysite.vo.GuestbookVo;;


public class GuestbookDao {

	
	//---------------------- DB에 insert -----------------------
	public boolean insert(GuestbookVo vo) {

		boolean result =false;
		Connection conn = null;
		PreparedStatement pstmt =null;

		try {
			
			conn = getConnection();

			//3. SQL 준비
			String sql = "insert into guestbook values(null,?,?,?,now());";
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			

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

	
	//---------------------- DB에서 데이터 가져오기 -----------------------
	public List<GuestbookVo> findAll(){

		List<GuestbookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;

		try {

			conn = getConnection();
			

			//3. SQL 준비
			String sql = "select no,name,password,contents,req_date from guestbook order by no desc;";
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩

			//5. SQL문 실행
			rs = pstmt.executeQuery();

			//6. 데이터가져오기
			while(rs.next()) {

				Long no =rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String contents = rs.getString(4);
				Date req_date = rs.getDate(5);

				GuestbookVo  vo = new GuestbookVo();
				vo.setNo(no);
			    vo.setName(name);
			    vo.setPassword(password);
			    vo.setContents(contents);
			    vo.setReq_date(req_date);
				
				list.add(vo);
			}


		}catch(SQLException e) {

			System.out.println("error"+e);
		}finally {

			try {

				if(rs!=null) {

					rs.close();
				}
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



		return list;
	}
	
	
	//---------------------- DB에서 데이터 삭제하기 -----------------------
	public boolean Delete(GuestbookVo vo) {
		
		boolean result =false;
		Connection conn = null;
		PreparedStatement pstmt =null;

		try {
			
			conn = getConnection();

			
			//3. SQL 준비
			String sql = "delete from guestbook where no=? and password=?;";
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
	
			//5. SQL문 실행
			int count = pstmt.executeUpdate();
			
			
			return true;
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
		
		return false;
	}


	private Connection getConnection() throws SQLException{

		Connection conn = null;

		try {
			//1. JDBC Driver 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			//2. 연결하기
			String url ="jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb","webdb");
		}catch(ClassNotFoundException e) {

			System.out.println("error-"+e);
			
		}

		return conn;


	}
}