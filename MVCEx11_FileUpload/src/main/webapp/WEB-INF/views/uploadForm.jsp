<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 1개 업로드 하기</title>
</head>
<body>
	<form action="uploadOk" method="post" enctype="multipart/form-data">
		<input type="file" name="file" id="file" required="required"> <br />
		<textarea name="content" id="content" cols="50" rows="5" required="required" placeholder="내용을 입력해라!!!"></textarea>
		<br />
		<input type="submit" value="업로드하기"/>
	</form>
</body>
</html>