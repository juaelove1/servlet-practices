<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.bitacademy.guestbook.dao.guestbookDao"%>
<%@page import="com.bitacademy.guestbook.vo.guestbookVo"%>
<%@page import="java.util.List" %>


<%
	guestbookDao dao = new guestbookDao();
	List<guestbookVo> list = dao.findAll();
%>





<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="/guestbook01/add.jsp" method="post">
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="contents" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>
	</form>
	<br>

	<!-- 회원정보 리스트 -->
	<table width=510 border=1>
		<%
			int i = 1;
			for (guestbookVo vo : list) {
		%>
		<tr>
			<td><%=i%></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getReq_date()%></td>
			<td><a href="/guestbook01/deleteform.jsp?no=<%=vo.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%=vo.getContents().replace("\r\n","<br>") %></td>
		</tr>
		<%
			i++;
			}
		%>
	</table>
</body>
</html>