<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>유저 가이드</title>
</head>
<style>
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
    a{
        font-size: larger;
        color:royalblue
    }
    img{
        width: 100%;
        height: 80%;
    }
</style>
<body>
	<div class="w3-content w3-container w3-margin-top w3-wide">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
                <h3>웹메모시스템 유저 가이드</h3>
                <br>
                <h4>가이드를 원하시는 항목을 선택하세요</h4>
			</div>
			<div>
                <a href=#none id="show" onclick="if(hide.style.display=='none') {hide.style.display='';show.innerText='▲접기'} else {hide.style.display='none';show.innerText='▶회원가입'}">▶회원가입</a>
                <div id="hide" style="display: none;"><img src="/resources/img/guide_img.001.png"></div>
            </div>
            <div>
                <a href=#none id="show2" onclick="if(hide2.style.display=='none') {hide2.style.display='';show2.innerText='▲접기'} else {hide2.style.display='none';show2.innerText='▶회원정보 수정'}">▶회원정보 수정</a>
                <div id="hide2" style="display: none;"><img src="/resources/img/guide_img.002.png"></div>
            </div>
             <div>
                <a href=#none id="show3" onclick="if(hide3.style.display=='none') {hide3.style.display='';show3.innerText='▲접기'} else {hide3.style.display='none';show3.innerText='▶Id/Pw 찾기'}">▶Id/Pw 찾기</a>
                <div id="hide3" style="display: none;"><img src="/resources/img/guide_img.003.png"></div>
            </div>
            <div>
                <a href=#none id="show4" onclick="if(hide4.style.display=='none') {hide4.style.display='';show4.innerText='▲접기'} else {hide4.style.display='none';show4.innerText='▶메모 작성'}">▶메모 작성</a>
                <div id="hide4" style="display: none;"><img src="/resources/img/guide_img.004.png"></div>
            </div>
            <div>
                <a href=#none id="show5" onclick="if(hide5.style.display=='none') {hide5.style.display='';show5.innerText='▲접기'} else {hide5.style.display='none';show5.innerText='▶메모 검색'}">▶메모 검색</a>
                <div id="hide5" style="display: none;"><img src="/resources/img/guide_img.005.png"></div>
            </div>
            <div>
                <a href=#none id="show6" onclick="if(hide6.style.display=='none') {hide6.style.display='';show6.innerText='▲접기'} else {hide6.style.display='none';show6.innerText='▶메모 상세 조회'}">▶메모 상세 조회</a>
                <div id="hide6" style="display: none;"><img src="/resources/img/guide_img.006.png"></div>
            </div>
            <div>
                <a href=#none id="show7" onclick="if(hide7.style.display=='none') {hide7.style.display='';show7.innerText='▲접기'} else {hide7.style.display='none';show7.innerText='▶양식 작성 및 조회'}">▶양식 작성 및 조회</a>
                <div id="hide7" style="display: none;"><img src="/resources/img/guide_img.007.png"></div>
            </div>
            <div class="w3-center w3-large w3-margin-top w3-margin-bottom">
                <button type="button" onclick="history.go(-1);" class="w3-button w3-hover-white w3-ripple w3-margin-top w3-round mybtn"> 뒤로가기</button>
            </div>
            
		</div>
	</div>
</body>

</html>