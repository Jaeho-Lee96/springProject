<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>#checkboxTestTbl {border-collapse: collapse;}
        #checkboxTestTbl td, #checkboxTestTbl th{padding:15px; font-size:12px;}
        #checkboxTestTbl th{background-color:  #0081cc;}
        .middle {left: 50%}
</style> 
<script src ="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

<title>메인 페이지</title>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top w3-wide">
		<div class="w3-container w3-card-4">
			<div class="w3-center w3-large w3-margin-top">
				<h3>웹메모시스템 메인</h3>
			</div>
			<div>
					<p>
						<div>
                            <button class="w3-button w3-round w3-margin-left w3-text-white w3-black" onclick="location='/User/authenticate'">정보수정</button>
                            <button class="w3-button w3-round w3-margin-left w3-text-white w3-black" onclick="location='/Login/logout'">로그아웃</button>
                        </div>
                    </p>
					<p>
                        <div  class="w3-content w3-large w3-margin-left">
                            <% 
                            String id = (String) session.getAttribute("id");
                            if (id == null)
        	                response.sendRedirect("/Login/login");
                            %>
                            ${id}님 안녕하세요!
                        </div>
					</p>
					<p>
                        <div>
                       <button type="button"  class="w3-button w3-round w3-blue w3-margin-left w3-text-white w3-wide w3-hover-indigo" onclick="location='/memoWrite'">메모작성</button>
                        <button type="button" class="w3-button w3-round w3-margin-left w3-blue w3-text-white w3-wide w3-hover-indigo" onclick="location='/memoListPageSearch?num=1'">메모검색</button>
                        <button type="button" class="w3-button w3-round w3-margin-left w3-blue w3-text-white w3-wide w3-hover-indigo" onclick="location='/memoFormList'">메모 양식 목록</button>
                       	<button type="button" class="w3-button w3-round w3-margin-left w3-blue w3-text-white w3-wide w3-hover-indigo" onclick="location='/memoFormAdd'">메모 양식 작성</button>
                        </div>
					</p>
					<p>
                        <div style="width: 30%;">
						<button type="button" class="w3-button w3-round w3-margin-left w3-blue w3-text-white w3-wide w3-hover-indigo" onclick="location='/Common/user_guide'">유저가이드보기</button>
                        </div>
					</p>
					<p>
					
						
					</p>
					<p>
					
					</p>
					<p>
						
                    </p>
                    	<div class="w3-center w3-large w3-margin-top">
                        즐겨찾기된 메모 리스트
                        <table class="middle" id="checkboxTestTbl" border="1px">
        <colgroup>
            <col width="350px;"/>
            <col width="200px;"/>
            <col width="250px;"/>
        </colgroup>
        <tr>
            <th>제목</th>
            <th>작성자</th>
            <th>등록일시</th>
        </tr>
		<c:forEach items="${memoList}" var="list">
		    <tr>
            	<td>
            	<a href="/memoView?memoNum=${list.MEMO_NUM}">${list.MEMO_NAME}</a>
            	</td>
            	<td>${list.USER_ID}</td>
            	<td>${list.MEMO_WRITE_DATE}</td>
	        </tr>
		</c:forEach>
    </table>
                        </div>      
					<p>
			</div>
		</div>
	</div>
</body>
</html>
