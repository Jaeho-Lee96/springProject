<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/addressapi.js"></script>
<script>
	function execPostCode() {
         new daum.Postcode({
			 theme: {
        			searchBgColor: "#0B65C8", //검색창 배경색
					queryTextColor: "#FFFFFF" //검색창 글자색
				},
             oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
 
                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수
 
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }
 
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                console.log(data.zonecode);
				console.log(fullRoadAddr);
				
                
                //$("[name=addr1]").val(data.zonecode);
                //$("[name=addr2]").val(fullRoadAddr); 
                
                document.getElementById('addr1').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('addr2').value = fullRoadAddr;
               // document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; 
            }
         }).open();
     }
</script>
<script>
$(function(){
	
		$("#email").keyup(function(){
			$.ajax({
				url : "/check_email.do",
				type : "POST",
				data : {
					email : $("#email").val()
				},
				success : function(result) {
					if (result == 1) {
						$("#email_check").html("중복된 이메일이 있습니다.");
					} else {
						$("#email_check").html("");
					}
				},
			})
		});
	})
</script>
	<title>개인정보 수정</title>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>개인정보 수정</h3>
			</div>
			<div>
				<form id="joinForm"  method="post">
					<p>
						<label>ID</label> 
						<input class="w3-input" type="text" id="id" name="userId" readonly placeholder="${ id }">
					</p>
					<p>
						<label>Name</label>
						<input type="text" id="name" name="userName" class="w3-input" readonly placeholder="${ name }">
					</p>
					<p>
						<label>Phone</label>
						<input type="tel" id="phone" name="userPhone" class="w3-input"  placeholder="변경할 번호를 입력해주세요"> 
					</p>
					<p>
						<label>Birthdate</label>
						<input type="text" id="birthdate" name="userBirthDate" class="w3-input" readonly placeholder="${ birthdate }">
					</p>
					<p>
						<label>Email</label>
						<input type="text" id="email" name="userEmail" class="w3-input" placeholder="변경할 이메일을 입력해주세요">
						<span id="email_check" class="w3-text-red"></span>
					</p>
					<p>
						<label>Address</label>
						<div class="form-group">                   
						<input class="w3-input" style="width: 40%; display: inline;" placeholder="우편번호" name="userAddress" id="addr1" type="text" readonly="readonly" >
    					<button type="button" class="btn btn-default" onclick="execPostCode();"><i class="fa fa-search"></i> 우편번호 찾기</button>                               
						</div>
						<div class="form-group">
    					<input class="w3-input" style="top: 5px;" placeholder="도로명 주소" name="userAddress" id="addr2" type="text" readonly="readonly" />
						</div>
						<div class="form-group">
    					<input class="w3-input" placeholder="상세주소" name="userAddress" id="addr3" type="text"  />
						</div>
					</p>
					
					<p>
						<label>Gender</label>
						<input type="text" id="gender" name="userGender" class="w3-input" placeholder="${ gender }">
					</p>
					<p class="w3-center">
						<button type="submit" id="joinBtn" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round" style="background-color: dodgerblue !important;">수정완료</button>
						<button type="button" onclick="history.go(-1);" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-margin-bottom w3-round">뒤로가기</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
