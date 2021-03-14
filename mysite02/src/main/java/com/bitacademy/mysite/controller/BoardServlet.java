package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bitacademy.mysite.dao.BoardDao;
import com.bitacademy.mysite.vo.BoardVo;
import com.bitacademy.mysite.vo.Uservo;
import com.bitacademy.web.mvc.WebUtil;

public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("a");
		
		if("writeform".equals(action)) { //글작성form
			
			WebUtil.forward("/WEB-INF/views/board/write.jsp", request, response);
			
		}else if("write".equals(action)) { //글작성하기
			
			String title = request.getParameter("title"); //글의 제목가져오기
			
			
			HttpSession session = request.getSession(true);
			Uservo authUser = (Uservo)session.getAttribute("authUser");
			String writer = authUser.getName();//작성자가져오기
			
			
			String content = request.getParameter("content"); //글의 내용가져오기
			
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			vo.setView(0);
			
			BoardDao dao = new BoardDao();
			dao.insert(vo);
				
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
			
			
		}else if("delete".equals(action)) { //글삭제하기
			
			String no = request.getParameter("no");
		    int number = Integer.parseInt(no);

			
			BoardVo vo = new BoardVo();
			vo.setNo(number);

			new BoardDao().Delete(vo);
				
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
			
			
		}else if("view".equals(action)) {//게시물보기
			
			String no = request.getParameter("no");
		    int number = Integer.parseInt(no);
			
			BoardVo vo = new BoardVo();
			vo.setNo(number);

			try {
				
				List<BoardVo> list = new BoardDao().Findcontent(vo);
				request.setAttribute("list", list);
			    new BoardDao().View(number);
				
			} catch (SQLException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
			
		}else if("modifyform".equals(action)) { // 글수정form으로 이동
			
			String no = request.getParameter("no");
		    int number = Integer.parseInt(no);
			
			BoardVo vo = new BoardVo();
			vo.setNo(number);
			
			List<BoardVo> list;
			try {
				
				list = new BoardDao().Findcontent(vo);
				request.setAttribute("list", list);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

									
			WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
			
		}else if("modify".equals(action)) {
			
			String title = request.getParameter("title"); // 수정한글의 제목가져오기
			String content = request.getParameter("content");// 수정한 글의 내용가죠오기
			String no = request.getParameter("no");//글의 번호가져오기
			
			int number = Integer.parseInt(no);
			
			
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setNo(number);

			new BoardDao().Update(vo);
				
			WebUtil.redirect(request.getContextPath() + "/board", request, response);
			
			
		}else { // 글목록보기
						
            List<BoardVo> list = new BoardDao().findAll();
								
			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			WebUtil.forward("/WEB-INF/views/board/index.jsp", request, response);
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}