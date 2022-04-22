<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web Mvc2 BoardDetail **</title>
</head>
<body>
<h2>** Web Mvc2 BoardDetail **</h2>
<table>
	<tr height="40">
		<td bgcolor="Lavender">Seq</td><td>${apple.seq}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">I D</td><td>${apple.id}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Title</td><td>${apple.title}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Content</td>
		<td><textarea rows="5" cols="50" readonly>${apple.content}</textarea></td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">RegDate</td><td>${apple.regdate}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">조회수</td><td>${apple.cnt}</td>
	</tr>
</table>
<c:if test="${not empty message}">
<hr>
=> ${message}<br>
</c:if>
<!-- 1) 글수정, 글삭제
		-> 내가 쓴글인 경우, 관리자 인경우 에만 가능 
		-> loginID 와 apple.id 가 동일한 경우
 -->
 <hr>
<c:if test="${loginID==apple.id  || loginID=='admin'}">
	<a href="/Web02/bdetail?jcode=U&seq=${apple.seq}">글수정</a>&nbsp;&nbsp;
	<a href="/Web02/bdelete?seq=${apple.seq}">글삭제</a>
</c:if> 
<hr>
<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;&nbsp;
<a href="/Web02/index.jsp">HOME</a>
</body>
</html>