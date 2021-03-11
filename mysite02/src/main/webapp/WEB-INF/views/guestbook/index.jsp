<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.bitacademy.mysite.dao.GuestbookDao"%>
<%@page import="com.bitacademy.mysite.vo.GuestbookVo"%>
<%@page import="java.util.List"%>


<%
	List<GuestbookVo> list = (List<GuestbookVo>) request.getAttribute("list");
%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath()%>/guestbook?a=add" method="post">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="contents" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				 
				 <ul>
					<li>
				 
						<!-- 회원정보 리스트 -->
						<table width=510 border=1>
							<%
								int i = 1;
								for (GuestbookVo vo : list) {
							%>
							<tr>
								<td><%=i%></td>
								<td><%=vo.getName()%></td>
								<td><%=vo.getReq_date()%></td>
								<td><a
									href="<%=request.getContextPath()%>/guestbook?a=deleteform&no=<%=vo.getNo()%>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4><%=vo.getContents().replace("\r\n", "<br>")%></td>
							</tr>
							<%
								i++;
								}
							%>
						</table> 	
						<br>
					</li>
				</ul>
		</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>