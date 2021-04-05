<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 조회</title>
<script src = "${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script>	
	function formDelete(FORM_ID) {
		if("${user}" == "${form.OWN_USER_ID}") {
			if(confirm("삭제하시겠습니까?")) {
				location.href='/formDelete?formId=' + ${form.FORM_ID};
			}
		} else {
			alert("삭제할 수 있는 권한이 없습니다.");
		}
	}

</script>
<style>
body {
	padding-top: 70px;
	padding-bottom: 30px;
}

.memo_title {
	font-weight : 700;
	font-size : 22pt;
	margin : 10pt;
}

.memo_info_box {
	color : #6B6B6B;
	margin : 10pt;
}

.memo_author {
	font-size : 10pt;
	margin-right : 10pt;

}
.memo_date {

	font-size : 10pt;
}

.memo_content {

	color : #444343;

	font-size : 12pt;

	margin : 10pt;
	
	padding-bottom: 10pt;
}
.memo_tag {
	font-size : 11pt;
	margin : 10pt;
	padding-bottom : 10pt;
}

hr {
	height: 1px;
	background-color: #0081cc;
}

.btn_color {
	background-color: #0081cc;
	color:white;
	border:0;
	outline:0;
}
.deleteBtn {
	background-color: #FF4500; 
	color:white;
	border:0; 
	outline:0;
}
</style>
</head>
	<body>
	<div class="memo_title">
		<p>${form.FORM_NAME}</p>
	</div>
	<hr>
	<div class="memo_cotent">
	<p>${form.FORM_DATA}</p>
	</div>
	<button class="deleteBtn" onclick="formDelete(${form.FORM_ID});">삭제</button>
	<button class="btn_color" onclick="history.go(-1);">뒤로가기</button>
	
	</body>

</html>