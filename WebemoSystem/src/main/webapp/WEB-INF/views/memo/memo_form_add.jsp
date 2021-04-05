<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 양식 작성</title>
<style>
hr {
	height: 1px;
	background-color: #0081cc;
}
.colorBtn {background-color: #F0F8FF; border:0;}
</style>
<script src = "${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
</head>
	<body>
	<button class="colorBtn" onclick="location='/Common/main'">메인으로</button>
	<form action="memoFormAdd" method="post">
		<input name="formName" type="text" value="양식 제목을 입력하세요."/>
		<hr>
		<textarea name="formData"></textarea>
		<script>CKEDITOR.replace("formData")</script>
		<input class="colorBtn" type="submit" value="작성">
	 	</form>
	</body>
</html>