<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web MVC2 BoardInsert Form **</title>
</head>
<body>
<h3>** Web MVC2 BoardInsert Form **</h3>
<form action="/Web02/binsert" method="post">
<table>
	<tr height="40"><td bgcolor="HoneyDew ">I D</td>
		<td><input type="text" name="id" value="${loginID}" size="20" readonly></td></tr>
	<tr height="40"><td bgcolor="HoneyDew ">Title</td>
		<td><input type="text" name="title"></td></tr>	
	<tr height="40"><td bgcolor="HoneyDew ">Content</td>
		<td><textarea name="content" rows="5" cols="50"></textarea></td></tr>
	<tr height="40"><td></td>
		<td><input type="submit" value="등록">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td></tr>			
</table>
</form>
<c:if test="${not empty message}">
<br>=> ${message}<br><br> 
</c:if>
<hr>
<a href="/Web02/blist">bList</a>&nbsp;&nbsp;
<a href="/Web02/index.jsp" >[Home]</a>
</body>
</html>