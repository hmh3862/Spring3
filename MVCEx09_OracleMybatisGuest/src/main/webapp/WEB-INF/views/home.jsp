<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	<h2>서버 시간 : ${serverTime }</h2>
	<h2>DB 시간 : <fmt:formatDate value="${dbTime }" pattern="yyyy년 MM월 dd일(E) hh:mm:ss"/> </h2>
	<hr />
	<a href="list">방명록 가기</a>
</body>
</html>
