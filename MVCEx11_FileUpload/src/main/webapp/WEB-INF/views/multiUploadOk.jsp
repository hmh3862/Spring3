<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러개 파일 업로드 확인</title>
<script type="text/javascript">
	function fileDown(ofile, sfile){
		location.href='download?of=' + encodeURI(ofile) + "&sf=" + encodeURI(sfile);
	}
</script>
</head>
<body>
	${result }
	
	<br />
	<hr />
	<a href="${pageContext.request.contextPath }">홈으로</a>
	
</body>
</html>