<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web_MVC2 BoardList</title>
<link rel="stylesheet" type="text/css" href="/Web02/myLib/myStyle.css">
</head>
<body>
<h3>** Web_MVC2 BoardList **</h3>
<br>
<c:if test="${not empty message}">
=> ${message}<br>
</c:if>
<hr>
<table width=100%>
<tr height="30" bgcolor="GreenYellow">
	<th>Seq</th><th>Title</th><th>Id</th><th>RegDate</th><th>조회수</th> 
</tr>	
<c:forEach var="list" items="${banana}">
<tr  height="30" align="center">
	<td>${list.seq}</td>
	<td align="left">
	 <!-- 글내용보기 기능 추가하기 -> login한 경우에만 허용 -->
	<c:if test="${not empty loginID}">
		<a href="/Web02/bdetail?seq=${list.seq}">${list.title}</a>
	</c:if>
	<c:if test="${empty loginID}">
		${list.title} 
	</c:if>
	</td>
	<td align="left">${list.id}</td><td>${list.regdate}</td><td>${list.cnt}</td>
</tr>
</c:forEach>
</table>
<hr>
<c:if test="${not empty loginID}">
	<a href="/Web02/board/binsertForm.jsp">새글등록</a><br>
</c:if>
<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;&nbsp;
<a href="/Web02/index.jsp" >[Home]</a>
</body>
</html>