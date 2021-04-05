<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
<title>회원정보 수정</title>
</head>
<body>
<form id="wdForm" action="/delete.do" method="post">
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4 w3-auto" style="width: 382px;height: 456.3px;">
			<div class="w3-center w3-large w3-margin-top">
                <p>
                <h3>회원정보 수정</h3>
                </p>
			</div>
			<div>
                <p class="w3-center">
                     <% 
                     String id = (String) session.getAttribute("id");
                    if (id == null)
        	        response.sendRedirect("initial");
                    %>
                    ${id}님이 변경하실 사항을 고르세요.
                </p>
				<p class="w3-center">
					<button type="button" class="w3-button w3-blue w3-border-blue w3-hover-white w3-ripple w3-margin-top w3-round mybtn" onclick="location='/User/change_info'" >개인 정보 수정</button>
					<button type="button" class="w3-button w3-blue w3-border-blue w3-hover-white w3-ripple w3-margin-top w3-round mybtn" onclick="location='/User/change_pw'">PW 변경</button>
				</p>
			</div>
			<div style="height: 30%;">
			
			</div>
                <p class="w3-center">
                    <button type="button" onclick="history.go(-1);" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-margin-bottom w3-round">뒤로가기</button>
                    <button name="bbtn" type="button" onclick="del();" id="delBtn" class="w3-button w3-hover-orange w3-block w3-ripple w3-margin-top w3-margin-bottom w3-round w3-red" >회원탈퇴</button>
                </p>
		</div>
	</div>
	</form>
</body>
<script type="text/javascript">
function del(){
	var result = confirm("정말 탈퇴하시겠습니까?");
	if(result == true){
		$("#wdForm").submit();
	}
	else{
		return false;
	}
}</script>
</html>