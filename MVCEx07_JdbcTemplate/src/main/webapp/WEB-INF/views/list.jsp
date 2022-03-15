<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 목록</title>
<link href="webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<script type="text/javascript" src="webjars/bootstrap/5.1.3/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/jquery/3.6.0/dist/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$("#cancelBtn").css("display","none");
	});
	
	function updateForm(idx, name, section){
		$("#cancelBtn").css("display","inline");
		$("#submitBtn").val('수정');
		$("#idx").val(idx);
		$("#name").val(name);
		$("#section").val(section);
		$("#mode").val(2);
	}
	function deleteForm(idx){
		if(confirm(idx + "번 학생을 정말로 삭제하시겠습니까?")){
			location.href='delete?idx=' + idx;
		}
	}
	function resetForm(){
		$("#cancelBtn").css("display","none");
		$("#submitBtn").val('저장');
		$("#idx").val(0);
		$("#name").val("");
		$("#section").val("");
		$("#mode").val(1);
	}
	
</script>
<style type="text/css">
	table { width: 500px; margin: auto;}
	.title { border: none; text-align: center; font-size: 18pt;}
	.sub-title { border: none; text-align: right;}
	th {padding:5px; border: 1px solid gray; background-color: silver;text-align: center; font-weight: bold;}
	td {padding:5px; border: 1px solid gray; text-align: center;}
</style>
</head>
<body>
	<table>
		<tr>
			<td class="title" colspan="4">학생 목록</td>
		</tr>
		<tr>
			<td class="sub-title" colspan="4">${fn:length(list) }명</td>
		</tr>
		<tr>
			<td colspan="4" style="border: none;">
				<form action="updateOk" method="post">
					<input type="hidden" name="idx"  id="idx"  value="0" />
					<input type="hidden" name="m"    id="mode" value="1" /> <%-- 1.저장, 2.수정 --%>
					<input type="text" name="name" id="name" required="required" placeholder="이름 입력">
					<input type="text" name="section" id="section" required="required" placeholder="학과 입력">
					<input type="submit" value="저장" id="submitBtn" class="btn btn-outline-success btn-sm" >
					<input type="button" value="취소" id="cancelBtn" class="btn btn-outline-success btn-sm" onclick="resetForm()">
				</form>
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>학과</th>
			<th>수정/삭제</th>
		</tr>
		<c:if test="${empty list }">
			<tr>
				<td colspan="4" style="text-align: center;">등록된 학생이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty list }">
			<c:forEach var="vo" items="${list }" varStatus="vs">
				<tr>
					<td>${fn:length(list) - vs.index }</td>
					<td>${vo.name }</td>
					<td>${vo.section }</td>
					<td>
						<button class="btn btn-outline-success btn-sm" onclick="updateForm(${vo.idx},'${vo.name }','${vo.section }')" >수정</button>
						<button class="btn btn-outline-success btn-sm" onclick="deleteForm(${vo.idx})">삭제</button>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>