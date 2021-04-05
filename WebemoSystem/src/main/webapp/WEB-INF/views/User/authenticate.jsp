<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
.mybtn{
  width:150px;
  height:40px;
  padding:0;
  display:inline; 
  border-radius: 4px; 
  background: #212529;
  color: #fff;
  margin-top: 20px;
  border: solid 2px #212529; 
  transition: all 0.5s ease-in-out 0s;
}
.mybtn:hover .mybtn:focus {
  background: white;
  color: #212529;
  text-decoration: none;
}
</style>
<title>회원정보 인증</title>
</head>
<body>
	<form method="post">
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4 w3-auto" style="width: 382px;height: 456.3px;">
			<div class="w3-center w3-large w3-margin-top">
                <p>
                <h3>회원정보 인증</h3>
                </p>
			</div>
			<div>
				<p>
					<label>PW</label>
					<input class="w3-input" type="password" id="pw" name="userPw" placeholder="회원정보 수정을 위해 현재 PW를 입력하세요" required>
				</p>
				<p class="w3-center">
					<button type="submit" class="w3-button w3-hover-white w3-ripple w3-margin-top w3-round mybtn" style="background-color: dodgerblue; border-width:2px; border-color:dodgerblue !important" >확인</button>
					<button type="button" onclick="history.go(-1);" class="w3-button w3-hover-white w3-ripple w3-margin-top w3-round mybtn">뒤로가기</button>
				</p>
			</div>
		</div>
	</div>
	</form>
</body>
</html>