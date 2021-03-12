package com.bitacademy.mysite.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bitacademy.mysite.dao.GuestbookDao;
import com.bitacademy.mysite.vo.GuestbookVo;
import com.bitacademy.web.mvc.WebUtil;

public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("a");
		
		if("deleteform".equals(action)) {
			
			WebUtil.forward("/WEB-INF/views/guestbook/deleteform.jsp", request, response);
			
		}else if("delete".equals(action)) { //delete하기
			
			String no = request.getParameter("no");
		    Long number = Long.parseLong(no);
			
			String password = request.getParameter("password");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(number);
			vo.setPassword(password);

			new GuestbookDao().Delete(vo);
				
			WebUtil.redirect(request.getContextPath() + "/guestbook", request, response);
			
		}else if("add".equals(action)) { //글등록
			
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
					
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);

			new GuestbookDao().insert(vo);
			
			WebUtil.redirect(request.getContextPath() + "/guestbook", request, response);
			
		} else { //index
			
			List<GuestbookVo> list = new GuestbookDao().findAll();
			
			// forwarding = request dispatch = request extension
			request.setAttribute("list", list);
			WebUtil.forward("/WEB-INF/views/guestbook/index.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}