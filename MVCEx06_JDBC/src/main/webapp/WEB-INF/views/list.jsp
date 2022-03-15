<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
	<script type="text/javascript" src="webjars/bootstrap/5.1.3/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="webjars/jquery/3.6.0/dist/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#cancelBtn").css('display','none'); // 시작시 취소 버튼은 보이지 않는다.
		});
		// 삭제 버튼을 누르면
		function deleteEmp(idx){
			if(confirm(idx + '번을 정말로 삭제하시겠습니까?') ){
				location.href='delete?idx='+idx;
			}
		}
		// 수정버튼을 누르면
		function updateEmp(idx, name, role){
			// alert(idx + "\n" + name + "\n" + role);
			$("#cancelBtn").css('display','inline'); // 수정시 취소 버튼은 보여야 않는다.
			$("#submitBtn").val("수정"); // 버튼의 제목이 "수정"으로 변경
			$("#idx").val(idx);
			$("#mode").val(2);
			$("#name").val(name);
			$("#role").val(role);
		}
		// 취소버튼을 누르면
		function resetForm(){
			$("#cancelBtn").css('display','none'); // 취소 버튼은 보이지 않는다.
			$("#submitBtn").val("저장"); // 버튼의 제목이 "저장"으로 변경
			$("#idx").val("0");
			$("#mode").val("1");
			$("#name").val("");
			$("#role").val("");
		}
	</script>
	<style type="text/css">
		table{ width: 550px; margin: auto;}
		th{ padding: 5px; text-align: center;border: 1px solid gray; background-color: silver;}
		td{ padding: 5px; text-align: center;border: 1px solid gray; background-color: white;}
	</style>
</head>
<body>
	<table>
		<tr>
			<td colspan="4" style="text-align: right; border: none;">${totalCount }개</td>
		</tr>
		<tr>
			<th>idx</th>
			<th>name</th>
			<th>role</th>
			<th>수정/삭제</th>
		</tr>
		<c:if test="${not empty list }">
			<c:forEach var="emp" items="${list }">
				<tr align="center">
					<td>${emp.idx }</td>
					<td>${emp.name }</td>
					<td>${emp.role }</td>
					<td>
						<input type="button" value="수정" class="btn btn-danger btn-sm" onclick="updateEmp(${emp.idx},'${emp.name}','${emp.role }')">
						<input type="button" value="삭제" class="btn btn-danger btn-sm" onclick="deleteEmp(${emp.idx})">
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty list }">
			<tr>
				<td colspan="4">등록된 항목이 없습니다.</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="4">
				<form action="updateOk" method="post">
					<input type="hidden" name="idx" value="0" id="idx">
					<input type="hidden" name="mode" value="1" id="mode">
					<input type="text" name="name"  id="name" required="required" placeholder="이름"/>		
					<input type="text" name="role" id="role" required="required" placeholder="직업"/>		
					<input type="submit" id="submitBtn" value="저장" class="btn btn-danger btn-sm">
					<input type="button" id="cancelBtn" value="취소" class="btn btn-danger btn-sm" onclick="resetForm()">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>