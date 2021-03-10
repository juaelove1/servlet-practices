<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.bitacademy.guestbook.dao.guestbookDao"%>
<%@page import="com.bitacademy.guestbook.vo.guestbookVo"%>


<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String contents = request.getParameter("contents");

	guestbookVo vo = new guestbookVo();
	vo.setName(name);
	vo.setPassword(password);
	vo.setContents(contents);

	new guestbookDao().insert(vo);

	response.sendRedirect("/guestbook01");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>