<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style type="text/css">
.title {
	border: 1px solid gray;
	background-color: silver;
	padding: 10px;
	cursor: pointer;
	border-top-right-radius: 10px;
	border-top-left-radius: 10px;
}

.content {
	border: 1px solid gray;
	padding: 10px;
	border-bottom-right-radius: 10px;
	border-bottom-left-radius: 10px;
}

.reply {
	border: 1px solid gray;
	padding: 10px;
	margin: 10px;
	display: none;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".title").click(function() {
			if ($(this).next().css('display') == 'block') {
				$(this).next().slideUp(1000)
			} else {
				$(this).next().slideDown(1000);
			}
		});

		$("button").click(function() {
			if ($(this).next().css('display') == 'block') {
				$(this).next().css('display', 'none');
			} else {
				$(this).next().css('display', 'block');
			}
		});
		$("[value='취소']").click(function() {
			$(this).parent().parent().fadeOut();
		});
	});
</script>
</head>
<body>
	<div>
		<div class="title">눌러봐</div>
		<div class="content">
			나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br>
			나는 내용일거야 하하하하<br>
			<button>답변달기</button>
			<div class="reply">
				<form action="">
					<input type="text" /> <input type="text" /> <input type="text" />
					<input type="submit" value="저장" /> <input type="button" value="취소" />
				</form>
			</div>
		</div>
	</div>
	<br />
	<div style="margin-left: 50px;">
		<div class="title">[답변] 눌러봐</div>
		<div class="content">
			나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br>
			나는 내용일거야 하하하하<br>
			<button>답변달기</button>
			<div class="reply">여기는 답변 폼</div>
		</div>
	</div>
	<br />
	<div style="margin-left: 100px;">
		<div class="title">[답변] 눌러봐</div>
		<div class="content">
			나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br>
			나는 내용일거야 하하하하<br>
			<button>답변달기</button>
			<div class="reply">여기는 답변 폼</div>
		</div>
	</div>
	<br />
	<div style="margin-left: 150px;">
		<div class="title">[답변] 눌러봐</div>
		<div class="content">
			나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br> 나는 내용일거야 하하하하<br>
			나는 내용일거야 하하하하<br>
			<button>답변달기</button>
			<div class="reply">여기는 답변 폼</div>
		</div>
	</div>
</body>
</html>