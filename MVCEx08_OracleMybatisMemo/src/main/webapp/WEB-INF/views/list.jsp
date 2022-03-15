<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>한줄메모장 Ver 0.9</title>
<%-- axicon 사용하기 --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/axicon/axicon.min.css" />

<link href="webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
<script type="text/javascript" src="webjars/bootstrap/5.1.3/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/jquery/3.6.0/dist/jquery.js"></script>
<%-- 사용자 정의 자바스크립트 함수를 추가한다. --%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/commons.js"></script>
<script type="text/javascript">
	$(function(){
		$("#cancelBtn").css("display","none");
		
		$("#selectView").change(function(){
			sendPost("list", {"p": ${pv.currentPage} ,"s": $(this).val() ,"b": ${pv.blockSize} })
		});
	});
	
</script>
<style type="text/css">
	table { width: 900px; margin: auto;}
	.title { border: none; text-align: center; font-size: 18pt;}
	.sub-title { border: none; text-align: right;}
	th {padding:5px; border: 1px solid gray; background-color: silver;text-align: center; font-weight: bold;}
	td {padding:5px; border: 1px solid gray; text-align: center;}
</style>
</head>
<body>
	<table>
		<tr>
			<td class="title" colspan="5">한줄메모장 프로그램 Ver 0.9</td>
		</tr>
		<tr>
			<td class="sub-title" colspan="5">
				${pv.pageInfo}
				<select id="selectView">
					<option value="5" ${pv.pageSize==5 ? " selected='selected' " : ""}>&nbsp;&nbsp;&nbsp;&nbsp;5</option>
					<option value="10" ${pv.pageSize==10 ? " selected='selected' " : ""}>&nbsp;&nbsp;&nbsp;10</option>
					<option value="15" ${pv.pageSize==15 ? " selected='selected' " : ""}>&nbsp;&nbsp;&nbsp;15</option>
					<option value="20" ${pv.pageSize==20 ? " selected='selected' " : ""}>&nbsp;&nbsp;&nbsp;20</option>
					<option value="50" ${pv.pageSize==50 ? " selected='selected' " : ""}>&nbsp;&nbsp;&nbsp;50</option>
					<option value="100" ${pv.pageSize==100 ? " selected='selected' " : ""}>&nbsp;&nbsp;100</option>
				</select>
				개씩 보기
			</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th width="60%">내용</th>
			<th>작성일</th>
			<th>ip</th>
		</tr>
		<c:if test="${empty pv.list }">
			<tr>
				<td colspan="5" style="text-align: center;">등록된 내용이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${not empty pv.list }">
			<c:forEach var="vo" items="${pv.list }" varStatus="vs">
				<c:set var="startNo" value="${pv.totalCount - (pv.currentPage-1)*pv.pageSize }"/> <%-- 시작번호 계산 --%>
				<tr>
					<td>${startNo - vs.index }</td> <%-- 줄여가면서 번호 출력 --%>
					<td> 
						<c:out value="${vo.name }"></c:out>
					</td>
					<td style="text-align: left;padding-left: 10px;">
						<c:out value="${vo.content }"></c:out>
						<%-- 새로운 글일경우 new 아이콘을 표시해 보자!!! --%>
						<jsp:useBean id="today" class="java.util.Date"/> <%-- 현재 시간 등록 --%>
						<fmt:parseNumber value="${today.time-(1000*60*60*3) }" var="n1"/> <%-- 현재 시간을 1/1000초 단위 숫자로 만들고 시간을 뺌 --%>
						<fmt:parseNumber value="${vo.regDate.time }" var="n2"/> <%-- 등록된 시간을 1/1000초 단위 숫자로 --%>
						<c:if test="${n1 lt n2 }"> <%-- 비교해서 new 아이콘 표시 : -(1000*60*60*3)는 3시간 전으로 세시간 내에 쓴글에만 아이콘표시  --%>
							<img alt="new" src="${pageContext.request.contextPath }/resources/images/new.jpg">
						</c:if>
						<button class="btn btn-outline-success btn-sm" onclick="updateForm(${vo.idx},'${vo.name }','${vo.content }')">수정</button>
						<button class="btn btn-outline-success btn-sm" onclick="deleteForm(${vo.idx},'${vo.name }','${vo.content }')">삭제</button>
					</td>
					<td>
						<%-- 오늘 쓴글은 시:분:초로 나타내고 --%>
						<%-- 올해 쓴글은 년-월-일 시:분 으로 나타내고 --%>
						<%-- 다른해 쓴글은 년-월-일로 나타내 보아라 --%>
						<!-- 1. 년도를 문자로 구해서 변수에 넣기 -->
						<fmt:formatDate value="${today }" pattern="yyyy" var="d1"/>
						<fmt:formatDate value="${vo.regDate }" pattern="yyyy" var="d2"/>
						<%-- 년도가 다르면 : 년-월-일 형식으로 출력--%>
						<c:if test="${d1 ne d2 }">
							<fmt:formatDate value="${vo.regDate }" pattern="yyyy-MM-dd"/> 
						</c:if>
						<%-- 2. 년도가 같다면 --%>
						<c:if test="${d1 eq d2 }">
							<%-- 월과 일자를 구한다. --%>
							<fmt:formatDate value="${today }" pattern="MMdd" var="d1"/>
							<fmt:formatDate value="${vo.regDate }" pattern="MMdd" var="d2"/>
							<%--월/일이 같은면 --%>
							<c:if test="${d1 eq d2 }">
								<fmt:formatDate value="${vo.regDate }" pattern="HH:mm:ss"/>
							</c:if>
							<c:if test="${d1 ne d2 }">
								<fmt:formatDate value="${vo.regDate }" pattern="yy-MM-dd"/>
							</c:if>
						</c:if>
					</td>
					<td>${vo.ip }</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<tr>
			<td colspan="5" style="border: none;">
				${pv.pageList }
			</td>
		</tr>
		<tr>
			<td colspan="4" style="border: none;">
				<form action="updateOk" method="post">
					<input type="hidden" name="idx"  id="idx"  value="0" />
					<input type="hidden" name="p"  id="p" value="${pv.currentPage }" />
					<input type="hidden" name="s"  id="s" value="${pv.pageSize }" />
					<input type="hidden" name="b"  id="b" value="${pv.blockSize }" />
					<input type="hidden" name="ip" id="ip" value="${pageContext.request.remoteAddr }" />
					<input type="hidden" name="m"  id="m" value="1" /> <%-- 1.저장, 2.수정 3. 삭제 --%>
					<input type="text" name="name" id="name" required="required" placeholder="이름 입력" size="6">
					<input type="password" name="password" id="password" required="required" placeholder="비번 입력" size="6">
					<input type="text" name="content" id="content" required="required" placeholder="내용 입력" size="60">
					<input type="submit" value="저장" id="submitBtn" class="btn btn-outline-success btn-sm" >
					<input type="button" value="취소" id="cancelBtn" class="btn btn-outline-success btn-sm" onclick="resetForm()">
				</form>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
	function updateForm(idx, name, content){
		$("#cancelBtn").css("display","inline");
		$("#submitBtn").val('수정');
		$("#idx").val(idx);
		$("#name").val(name);
		$("#content").val(content);
		$("#m").val(2);
		$("#password").focus();
	}
	function deleteForm(idx, name, content){
		$("#cancelBtn").css("display","inline");
		$("#submitBtn").val('삭제');
		$("#idx").val(idx);
		$("#name").val(name);
		$("#content").val(content);
		$("#m").val(3);
		$("#password").focus();
	}
	function resetForm(){
		$("#cancelBtn").css("display","none");
		$("#submitBtn").val('저장');
		$("#idx").val(0);
		$("#name").val("");
		$("#content").val("");
		$("#m").val(1);
		$("#name").focus();
	}
	</script>
</body>
</html>