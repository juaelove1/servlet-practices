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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					
					<c:forEach items="${list}" var="list">

						<tr>
							<td class="label">제목</td>
							<td>${list.title}</td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<div class="view-content">
								    ${fn:replace(list.content,newline,"<br>")}
								</div>
							</td>
						</tr>
				   </c:forEach> 					
				</table>
	 
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board">글목록</a>
					
				<c:forEach items="${list}" var="list">
					<c:choose>
                           <c:when test="${authUser.name == list.writer}"> <!-- 본인글이면 -->
                                <a href="${pageContext.request.contextPath}/board?a=modifyform&no=${list.no}">글수정</a>
                           </c:when>
                       </c:choose>
               <!-- 로그인시에만 답글 버튼나오기 -->
			   <c:choose>
                 <c:when test="${!empty authUser}">
                    <a href="${pageContext.request.contextPath}/board?a=writeform">답글</a>	
                 </c:when>
               </c:choose>     
              </c:forEach>                   		
			 </div>
			</div>    	
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>