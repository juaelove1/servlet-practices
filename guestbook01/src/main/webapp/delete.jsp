<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.bitacademy.guestbook.vo.guestbookVo"%>
<%@page import="com.bitacademy.guestbook.dao.guestbookDao"%>

<%
	request.setCharacterEncoding("utf-8");
	String no = request.getParameter("no");
    Long number = Long.parseLong(no);
	
	String password = request.getParameter("password");

	
	guestbookVo vo = new guestbookVo();
	vo.setNo(number);
	vo.setPassword(password);

	
	new guestbookDao().Delete(vo);

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