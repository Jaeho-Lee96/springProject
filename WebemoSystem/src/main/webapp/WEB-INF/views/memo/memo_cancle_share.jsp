<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업 창</title>
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
        #checkboxTestTbl th{background-color: #0081cc; color: white;} 
        #checkboxTestTbl tr.selected{background-color: navy;color: #fff; font-size: 10px; font-weight: bold; }
	</style>
</head>
<body>
 <p>유저 목록</p>
 <table id="checkboxTestTbl" border="1px">
       <caption>유저 리스트</caption>
        <colgroup>
            <col width="40px;"/>
            <col width="100px;"/>
        </colgroup>
        <tr>
            <th><input type="checkbox"/></th>
            <th>유저 ID</th>
        </tr>
		<c:forEach items="${userList}" var="list">
		    <tr>
            	<td><input type="checkbox" name="userCheck" /></td>            	       
            	<td id="userId">${list.SHARED_USER_ID}</td>            	
	        </tr>
		</c:forEach>
    </table>
    <button type="button" id="shareCancleBtn" onclick="shareCancleMemo()">공유 취소</button>
    <script>
    	function shareCancleMemo() {
    		if(confirm("공유 취소 하시겠습니까?")) {
    			var tbl = $("#checkboxTestTbl:checkbox:not(:first)");
    			console.log("button clicked");
    			var cnt = 0;
    			var memoId = "${memoNum}";
			
    			var checkbox = $('input:checkbox[name=userCheck]:checked');

				
    			checkbox.each(function(i) {
        			var tr = checkbox.parent().parent().eq(i);
    				var td = tr.children();		
    				console.log("userId = " + td.eq(1).text())
    				$.ajax({
    					url: "/memoShareCanclePop",
    					data: {"memoNum" : memoId, "userId" : td.eq(1).text()},
    					type: "POST",
    					success: function(data){
    						if(cnt == i) {
    							alert("공유 취소 성공")	
    						} else {
    							cnt++;
    						}
    					
    					},
    					error: function(error){
    						alert("공유 취소 실패")	
    					}
    				})
    			});
    			
    	
    		}
    		setTimeout('window.close()', 500);

    	}

    </script>
</body>
</html>