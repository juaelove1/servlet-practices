package com.bitacademy.guestbook02.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitacademy.guestbook02.dao.guestbookDao;
import com.bitacademy.guestbook02.vo.guestbookVo;
import com.bitacademy.web.mvc.WebUtil;

public class guestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("a");

		if("deleteform".equals(action)) { //deleteform
			
			WebUtil.forward("/WEB-INF/views/deleteform.jsp", request, response);
			
		} else if("delete".equals(action)) { //delete하기
			
			String no = request.getParameter("no");
		    Long number = Long.parseLong(no);
			
			String password = request.getParameter("password");
			
			guestbookVo vo = new guestbookVo();
			vo.setNo(number);
			vo.setPassword(password);

			new guestbookDao().Delete(vo);
				
			WebUtil.redirect(request.getContextPath() + "/gb", request, response);
			
		}else if("add".equals(action)) { //회원등록
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");

			guestbookVo vo = new guestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);

			new guestbookDao().insert(vo);
			
			WebUtil.redirect(request.getContextPath() + "/gb", request, response);
			
		}else { //index
			
			List<guestbookVo> list = new guestbookDao().findAll();
			
			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			WebUtil.forward("/WEB-INF/views/index.jsp", request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}