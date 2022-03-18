<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업로드 결과 확인</title>
<script type="text/javascript">
	function fileDown(ofile, sfile){
		location.href='download?of=' + encodeURI(ofile) + "&sf=" + encodeURI(sfile);
	}
</script>
</head>
<body>
	saveName : ${saveName } <br />
	originalName : ${originalName } <br />
	contentType : ${contentType } <br />
	fileSize : ${fileSize } <br />
	folder : ${folder } <br />
	content: ${content } <br />
	<hr />
	<button onclick="fileDown('${originalName }','${saveName }')">다운로드</button>
	<hr />
	<a href="${pageContext.request.contextPath }">홈으로</a>
</body>
</html>