package com.bitacademy.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.bitacademy.mysite.vo.BoardVo;



public class BoardDao {
	
	//---------------------- DB에 insert(새글) -----------------------
		public boolean insert(BoardVo vo) {

			boolean result =false;
			Connection conn = null;
			PreparedStatement pstmt =null;

			try {
				
				conn = getConnection();

				//3. SQL 준비 
				String sql = "insert into board values("
						+ "null,?,?,?,0," // no,title,writer,content,view
						+ "(select ifnull(max(group_no)+1,1) from board a),"//group_no
						+ "1,0,now());"; // order_no,depth,date
				pstmt = conn.prepareStatement(sql);

				//4. 바인딩
				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getWriter());
				pstmt.setString(3, vo.getContent());

				
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
		public List<BoardVo> findAll(){

			List<BoardVo> list = new ArrayList<>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;

			try {

				conn = getConnection();
				

				//3. SQL 준비
				String sql = "select no,title,writer,view,depth,date from board order by group_no desc, order_no asc;";
				pstmt = conn.prepareStatement(sql);

				//4. 바인딩

				//5. SQL문 실행
				rs = pstmt.executeQuery();

				//6. 데이터가져오기
				while(rs.next()) {

					int no =rs.getInt(1);
					String title = rs.getString(2);
					String writer = rs.getString(3);
					int view = rs.getInt(4);
					int depth = rs.getInt(5);
					Date date = rs.getDate(6);

					BoardVo  vo = new BoardVo();
					vo.setNo(no);
				    vo.setTitle(title);
				    vo.setWriter(writer);
				    vo.setView(view);
				    vo.setDepth(depth);
				    vo.setDate(date);
					
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
		public boolean Delete(BoardVo vo) {
			
			Connection conn = null;
			PreparedStatement pstmt =null;

			try {
				
				conn = getConnection();

				
				//3. SQL 준비
				String sql = "delete from board where no=?;";
				pstmt = conn.prepareStatement(sql);

				//4. 바인딩
				pstmt.setLong(1, vo.getNo());
		
				//5. SQL문 실행
			    pstmt.executeUpdate();
				
				
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
		
		
		//---------------------- 게시물보기 -----------------------
				public List<BoardVo> Findcontent(BoardVo vo) throws SQLException{

					List<BoardVo> list = new ArrayList<>();
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs =null;

					try {

						conn = getConnection();
						

						//3. SQL 준비
						String sql = "select no,title,writer,content,group_no,order_no,depth from board where no=?";
						pstmt = conn.prepareStatement(sql);

						pstmt.setInt(1, vo.getNo());
						
						rs = pstmt.executeQuery();

						//6. 데이터가져오기
						while(rs.next()) {

							int no = rs.getInt(1);
							String title = rs.getString(2);
							String writer = rs.getString(3);
							String content = rs.getString(4);
							int group_no = rs.getInt(5);
							int order_no = rs.getInt(6);
							int depth = rs.getInt(7);

							BoardVo  boardvo = new BoardVo();
							
							boardvo.setNo(no);
							boardvo.setTitle(title);
							boardvo.setWriter(writer);
							boardvo.setContent(content);
							boardvo.setGroup_no(group_no);
							boardvo.setOrder_no(order_no);
							boardvo.setDepth(depth);
							
							list.add(boardvo);
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
				
				
				//---------------------- DB 내용 수정하기 -----------------------
				public boolean Update(BoardVo vo) {

					boolean result =false;
					Connection conn = null;
					PreparedStatement pstmt =null;

					try {
						
						conn = getConnection();

						//3. SQL 준비 
						String sql = "update board set title=?,content=? where no=?";
						pstmt = conn.prepareStatement(sql);

						//4. 바인딩
						pstmt.setString(1, vo.getTitle());
						pstmt.setString(2, vo.getContent());
						pstmt.setInt(3, vo.getNo());

						
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
				
				
				//---------------------- 조회수올리기 -----------------------
				public boolean View(int no) {

					boolean result =false;
					Connection conn = null;
					PreparedStatement pstmt =null;

					try {
						
						conn = getConnection();

						//3. SQL 준비 
						String sql = "update board set view=view+1 where no=?";
						pstmt = conn.prepareStatement(sql);

						//4. 바인딩
						pstmt.setInt(1, no);
						
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
				
				
				//---------------------- 답글쓸 글의 group_no,order_no,depth가져오기 -----------------------
				public List<BoardVo> FindInfo(int no) throws SQLException{

					List<BoardVo> list = new ArrayList<>();
					Connection conn = null;
					PreparedStatement pstmt = null;
					ResultSet rs =null;

					try {

						conn = getConnection();
						

						//3. SQL 준비
						String sql = "select group_no,order_no,depth from board where no=?";
						pstmt = conn.prepareStatement(sql);

						pstmt.setInt(1, no);
						
						rs = pstmt.executeQuery();

						//6. 데이터가져오기
					
					while(rs.next()) {
						
						int group_no = rs.getInt(1);
						int order_no = rs.getInt(2);
						int depth = rs.getInt(3);

						BoardVo  boardvo = new BoardVo();
							
						boardvo.setGroup_no(group_no);
						boardvo.setOrder_no(order_no);
						boardvo.setDepth(depth);
							
						list.add(boardvo);
						
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
				
				
				//---------------------- DB에 insert(답글) -----------------------
				public boolean Reply_insert(BoardVo vo) {

					boolean result =false;
					Connection conn = null;
					PreparedStatement pstmt =null;

					try {
						
						conn = getConnection();

						//3. SQL 준비 
						String sql = "insert into board values(null,?,?,?,0,?,?,?,now())";
						pstmt = conn.prepareStatement(sql);

						//4. 바인딩
						pstmt.setString(1, vo.getTitle());
						pstmt.setString(2, vo.getWriter());
						pstmt.setString(3, vo.getContent());
						pstmt.setInt(4, vo.getGroup_no());
						pstmt.setInt(5, vo.getOrder_no());
						pstmt.setInt(6, vo.getDepth());

						
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

		//2. 연결하기
		String url ="jdbc:mysql://localhost:3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
		conn = DriverManager.getConnection(url, "webdb","webdb");
	}catch(ClassNotFoundException e) {

		System.out.println("error-"+e);
		
	}

	return conn;
	
}
}