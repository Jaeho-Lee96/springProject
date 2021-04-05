<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ko">
<title>로그인</title>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel = "shortcut icon" href= "favicon.ico">
<link rel = "icon" href = "favicon.ico">

<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<title>로그인 폼</title>
<style>
@import url("http://fonts.googleapis.com/earlyaccess/nanumgothic.css");

html {
	height: 100%;
}

body {
	width: 100%;
	height: 100%;
	margin: 0;
	padding-top: 80px;
	padding-bottom: 40px;
	font-family: "Nanum Gothic", arial, helvetica, sans-serif;
	background-repeat: no-repeat;
	background: linear-gradient(to bottom right, #0098FF, #6BA8D1);
}

.card {
	margin: 0 auto; /* Added */
	float: none; /* Added */
	margin-bottom: 10px; /* Added */
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}
</style>

</head>

<body style="margin: 0 auto;" cellpadding="0" cellspacing="0"
	marginleft="0" margintop="0" width="100%" height="100%" align="right">

	<div class="card align-middle"
		style="width: 20rem; border-radius: 20px;">
		<div class="card-title" style="margin-top: 30px;">
			<h2 class="card-title text-center" style="color: #113366;">웹메모시스템</h2>
		</div>
		<div class="card-body">
			<form class="form-signin" method="post">
				<h5 class="form-signin-heading">로그인 정보를 입력하세요</h5>
				<div>
					<label for="inputId" class="sr-only">Your ID</label> <input
						type="text" id="userId" class="form-control" placeholder="Your ID"
						required autofocus name="userId"><BR>
				</div>
				<label for="inputPassword" class="sr-only">Password</label> <input
					type="password" id="userPw" class="form-control"
					placeholder="Password" name="userPw"><br>
				<div class="checkbox">
					<label> <input type="checkbox" value="remember-me">
						기억하기
					</label>
				</div>
				<button id="btn-Yes" class="btn btn-lg btn-primary btn-block"
					type="submit">로그인</button>
				<button id="btn-Yes" class="btn btn-lg btn-primary btn-block"
					type="button" style="background-color: #212529 !important;"
					onclick="location.href = '/User/regist'">회원가입</button>
				<div>
					<br>
					<button
						style="border-color: aliceblue; color: white; background-color: slategray; border-radius: 2em;"
						onclick="location='/User/find_id'">ID 찾기</button>
					<button
						style="border-color: aliceblue; color: white; background-color: slategray; border-radius: 2em;"
						onclick="location='/User/find_pw'">PW 찾기</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>