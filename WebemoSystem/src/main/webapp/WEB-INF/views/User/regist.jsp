<!-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
		var time = 180
	    var min = "";
	    var sec = "";
	    
	    
		$("#joinForm").submit(function(){
			if($("#pw").val() !== $("#pw2").val()){
				alert("비밀번호가 다릅니다.");
				$("#pw").val("").focus();
				$("#pw2").val("");
				return false;
			}else if ($("#pw").val().length < 8) {
				alert("비밀번호는 8자 이상으로 설정해야 합니다.");
				$("#pw").val("").focus();
				return false;
			}else if(isCertification == false){
				alert("이메일 인증이 완료되지않았습니다");
				return false;
			}
			else if($.trim($("#pw").val()) !== $("#pw").val() || $.trim($("#id").val()) !== $("#id").val()){
				alert("공백은 입력이 불가능합니다.");
				return false;
			}
		})
		
		$("#id").keyup(function() {
			$.ajax({
				url : "/check_id.do",
				type : "POST",
				data : {
					id : $("#id").val()
				},
				success : function(result) {
					if (result == 1) {
						$("#id_check").html("중복된 아이디가 있습니다.");
						$("#joinBtn").attr("disabled", "disabled");
					} else {
						$("#id_check").html("");
						$("#joinBtn").removeAttr("disabled");
					}
				},
			})
		});
		
		$("#userEmail").keyup(function(){
			$.ajax({
				url : "/check_email.do",
				type : "POST",
				data : {
					email : $("#userEmail").val()
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
		
		$("#transBtn").click(function() {// 메일 입력 유효성 검사
			var userEmail = $("#userEmail").val(); //사용자의 이메일 입력값. 
			
			if (userEmail == "") {
				alert("메일 주소가 입력되지 않았습니다.");
			} else {
				$.ajax({
					type : "POST",
					url : "/check_email_auth.do",
					dataType: "json",
					data : {
						userEmail:userEmail
						},
					dataType :"json",
					success:function(data){
						console.log(data);
						var key = data["key"];
						console.log("전송된 키값 = " + key);
						$("#hv").val(key);
						
						alert("인증번호가 전송되었습니다.");
						$("#hidden_div").attr("style","visiblity:visible");
						isCertification=true; //추후 인증 여부를 알기위한 값
					}, 
					error:function(request,status,error){
					    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					   }
				});

			    var x = setInterval(function(){
			        min = parseInt(time/60);
			        sec = time % 60;
			        document.getElementById("time").innerHTML= '<h4>'+ "남은시간 : " + min + "분" + sec + "초" + '</h4>';
			        time--;
			        if(time < 60){
			            document.getElementById("time").style.color = "red";
			        }
			        if(time < 0){
			            clearInterval(x);
			            alert("시간이 초과되었습니다 다시 인증해주세요");
			        }
			    }, 1000);
			    
			    $("#auBtn").click(function() {
					var key = $("#hv").val();
					console.log("저장된 키값 = " + key);
					if ($("#email_compare").val() == key) {   //인증 키 값을 비교를 위해 텍스트인풋과 벨류를 비교
						$("#transBtn").attr("disabled",true);
						$("#userEmail").attr("readonly",true);
						$("#hidden_div").attr("style","display:none");
						clearInterval(x);
						alert("인증되었습니다.");
						$("#compare_text").css("font-size", "20px");
						$("#compare_text").text("인증 성공!").css("color", "blue");
						isCertification = true;  //인증 성공여부 check
					} else {
						alert("인증번호를 다시 입력해주세요.");
						$("#email_compare").val("");
						isCertification = false; //인증 실패
					}
				});
			}
		});
		
	})
</script>
<title>회원가입</title>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>회원가입</h3>
			</div>
			<div>
				<form id="joinForm" action="/User/regist" method="post">
					<p>
						<label>ID</label> 
						<input class="w3-input" type="text" id="id" name="userId" required placeholder="ID를 입력해주세요"> 
						<span id="id_check" class="w3-text-red"></span>
					</p>
					<p>
						<label>Password</label> 
						<input class="w3-input" id="pw" name="userPw" type="password" required placeholder="패스워드를 8자리 이상으로 입력해주세요">
					</p>
					<p>
						<label>Password 확인</label> 
						<input class="w3-input" id="pw2" type="password" required  placeholder="패스워드를 위와 동일하게 입력해주세요">
					</p>
					<p>
						<label>Name</label>
						<input type="text" id="name" name="userName" class="w3-input" required placeholder="이름을 입력해주세요">
					</p>
					<p>
					
						<label>Phone</label>
						<input type="tel" id="phone" name="userPhone" class="w3-input" required placeholder="번호를 입력해주세요">
					</p>
					<p>
						<label>Birthdate</label>
						<input type="date" id="birthdate" name="userBirthDate" class="w3-input" required>
					</p>
					<p>
						<label>Email</label>
						<input type="text" id="userEmail" name="userEmail" class="w3-input" required placeholder="이메일을 입력해주세요">
						<span id="email_check" class="w3-text-red"></span>
						<button id="transBtn" type ="button" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round" style="width: 10%;" style="height: 10% !important;">전송</button>
						<div id=compare_text></div>
						<div id="hidden_div" style="visibility:hidden;">
						<input type="text" class="w3-round" name="email_comare" id="email_compare" placeholder="인증 번호 입력" /> 
    					<button id ="auBtn"type="button" class="w3-round w3-button w3-blue">인증</button><span id="time"></span>
    					</div>
    					<input type="hidden" id ="hv" name="hide" value= "">
    					
						</div>
					</p>
					<p>
						<label>Address</label>
						<div class="form-group">                   
						<input class="w3-input" style="width: 40%; display: inline;" placeholder="우편번호" id="addr1" type="text" readonly="readonly" >
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
						<select class="w3-input" id="gender" name="userGender">
                            <option value="M">남성</option>
                            <option value="F">여성</option>
						</select>
						<p>
							<label>개인정보취급방침</label>
                    <div class="w3-input" id="memberInfo">
                        <textarea class="w3-input" rows="3" style="resize:none">개인정보의 항목 및 수집방법
회사는 회원가입, 민원 등 고객상담 처리, 본인확인(14세 미만 아동 확인) 등 의사소통을 위한 정보 활용 및 이벤트 등과 같은 마케팅용도 활용, 회원의 서비스 이용에 대한 통계, 이용자들의 개인정보를 통한 서비스 개발을 위해 아래와 같은 개인정보를 수집하고 있습니다.

1. - 목적 : 이용자 식별 및 본인여부 확인
- 항목 : 이름, 아이디, 비밀번호,닉네임, 이메일, 휴대폰번호, 주소, 전화번호 등
- 보유 및 이용기간 : 회원탈퇴 후 5일까지

2. – 목적 : 민원 등 고객 고충처리
- 항목 : 이름, 아이디, 이메일, 휴대폰번호, 전화번호, 주소, 구매자정보,결제정보,상품 구매/취소/교환/반품/환불 정보, 수령인정보
- 보유 및 이용기간 : 회원탈퇴 후 5일까지

3. – 목적 : 만 14세 미만 아동 확인
- 항목 : 법정 생년월일
- 보유 및 이용기간 : 회원탈퇴 후 5일까지


이용자의 개인정보는 원칙적으로 개인정보의 수집 및 이용 목적 달성 시 지체없이 파기합니다. 다만 다른 법령에서 별도의 기간을 정하고 있는 경우나 이용자의 요청에 따라 기간을 달리 정한 경우에는 그 기간이 경과한 후 파기 등의 필요한 조치를 취합니다.

                    

                        </textarea>
                        <div class="radio">
                            <label>
                                <input type="radio" id="memberInfoYn" name="memberInfoYn" value="Y" checked>
                                동의합니다.
							</label>
							</div>
                        <div class="radio">
                            <label>
                                <input type="radio" id="memberInfoYn" name="memberInfoYn" value="N"disabled>
                                동의하지 않습니다.
                            </label>
                        </div>
                    </div>
						</p>
					
					<p class="w3-center">
						<button type="submit" id="joinBtn" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round" style="background-color: dodgerblue !important;">회원가입완료</button>
						<button type="button" onclick="history.go(-1);" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-margin-bottom w3-round">회원가입취소</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

