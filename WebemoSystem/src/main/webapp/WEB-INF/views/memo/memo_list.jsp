<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메모 목록</title>
	<script src ="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
	    <script>
        $(document).ready(function(){
            var tbl = $("#checkboxTestTbl");
             
            // 테이블 헤더에 있는 checkbox 클릭시
            $(":checkbox:first", tbl).click(function(){
                // 클릭한 체크박스가 체크상태인지 체크해제상태인지 판단
                if( $(this).is(":checked") ){
                    $(":checkbox", tbl).attr("checked", "checked");
                }
                else{
                    $(":checkbox", tbl).removeAttr("checked");
                }
 
                // 모든 체크박스에 change 이벤트 발생시키기               
                $(":checkbox", tbl).trigger("change");
            });
             
            // 헤더에 있는 체크박스외 다른 체크박스 클릭시
            $(":checkbox:not(:first)", tbl).click(function(){
                var allCnt = $(":checkbox:not(:first)", tbl).length;
                var checkedCnt = $(":checkbox:not(:first)", tbl).filter(":checked").length;
                 
                // 전체 체크박스 갯수와 현재 체크된 체크박스 갯수를 비교해서 헤더에 있는 체크박스 체크할지 말지 판단
                if( allCnt==checkedCnt ){
                    $(":checkbox:first", tbl).attr("checked", "checked");
                }
                else{
                    $(":checkbox:first", tbl).removeAttr("checked");
                }
            }).change(function(){
                if( $(this).is(":checked") ){
                    // 체크박스의 부모 > 부모 니까 tr 이 되고 tr 에 selected 라는 class 를 추가한다.
                    $(this).parent().parent().addClass("selected");
                }
                else{
                    $(this).parent().parent().removeClass("selected");
                }
            });
        });
    </script>

	<style>
	    #checkboxTestTbl {border-collapse: collapse;}
        #checkboxTestTbl td, #checkboxTestTbl th{padding:15px; font-size:12px;}
        #checkboxTestTbl th{background-color: #ccc;} 
        #checkboxTestTbl tr.selected{background-color: navy;color: #fff; font-size: 10px; font-weight: bold; }
	</style>
</head>
<body>
	<p>메모 리스트<p>
	<table id="checkboxTestTbl" border="1px">
       <caption>메모 리스트 및 체크 박스</caption>
        <colgroup>
            <col width="40px;"/>
            <col width="50px;"/>
            <col width="200px;"/>
            <col width="80px;"/>
            <col width="170px;"/>
            <col width="170px;"/>
        </colgroup>
        <tr>
            <th><input type="checkbox"/></th>
            <th>FAV</th>
            <th>제목</th>
            <th>작성자</th>
            <th>등록일</th>
            <th>수정일</th>
        </tr>
		<c:forEach items="${memoList}" var="list">
		    <tr>
            	<td><input type="checkbox" /></td>
            	<td>${list.MEMO_FAV}</td>
            	<td>
            	<a href="/webmemo/memoView?memoNum=${list.MEMO_NUM}">${list.MEMO_NAME}
            	</td>
            	<td>${list.USER_ID}</td>
            	<td>${list.MEMO_WRITE_DATE}</td>
           		<td>${list.MEMO_UPDATE_DATE}</td>
	        </tr>
		</c:forEach>
    </table>
    
</body>
</html>