<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
 
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
					<tr>
				  <c:set var="count" value= "${fn:length(list)}" />
				    <c:forEach items="${list}" var="vo" varStatus="status">
				 
						<!-- 게시판리스트 -->
							<tr>
								<td>${count-status.index}</td>
								 <c:choose>
                                     <c:when test = "${vo.depth>0}">
                                        <td><img style="margin-left: ${vo.depth*20}px; " id="reply" src="${pageContext.request.contextPath}/assets/images/reply.png"><a href="${pageContext.request.contextPath}/board?a=view&no=${vo.no}"><c:out value="${vo.title}" /></a></td>
                                     </c:when>
                                     <c:otherwise>
								         <td><a href="${pageContext.request.contextPath}/board?a=view&no=${vo.no}"><c:out value="${vo.title}" /></a></td>
								     </c:otherwise>
								</c:choose>         
								<td>${vo.writer}</td>
								<td>${vo.view}</td>
								<td>${vo.date}</td>
								<td>${vo.depth}</td>
						<c:choose>
                           <c:when test="${authUser.name == vo.writer}"> <!-- 본인글이면 -->
                             	<td><a href="${pageContext.request.contextPath}/board?a=delete&no=${vo.no}" class="del">삭제</a></td>
                           </c:when>
                           
                       </c:choose>    				
							</tr>	
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
				  	<ul>
						<li><a href="">◀</a></li>
						<li class="selected"><a href="/mysite02/board?p=1">1</a></li>
						<li><a href="/mysite02/board?p=2">2</a></li>
						<li><a href="/mysite02/board?p=3">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				<div class="bottom">
				  <!-- 로그인시에만 글쓰기 버튼나오기 -->
				        <c:choose>
                           <c:when test="${!empty authUser}">
                       <a href="${pageContext.request.contextPath}/board?a=writeform"  id="new-book">글쓰기</a>
                           </c:when>
                        </c:choose>    
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>