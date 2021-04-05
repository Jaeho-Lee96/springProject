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
        #checkboxTestTbl th{background-color: #0081cc; color: white;} 
        #checkboxTestTbl tr.selected{background-color: navy;color: #fff; font-size: 10px; font-weight: bold;}
        a { text-decoration:none; color:red}
        .deleteBtn {background-color: #FF4500; color:white; border:0; outline:0;}
        .colorBtn {background-color: #F0F8FF; border:0;}
        
	</style>
</head>
<body>
	<div>
		<button class="colorBtn" onclick="location='/Common/main'">메인으로</button>
		<button class="colorBtn" onclick="location='/Login/logout'">로그아웃</button>
	</div>
	<table id="checkboxTestTbl" border="1px">
       <caption>메모 리스트</caption>
        <colgroup>
            <col width="40px;"/>
            <col width="100px;"/>
            <col width="200px;"/>
            <col width="80px;"/>
            <col width="170px;"/>
            <col width="170px;"/>
        </colgroup>
        <tr>
            <th><input type="checkbox"/></th>
            <th>즐겨찾기</th>
            <th>제목</th>
            <th>작성자</th>
            <th>등록일시</th>
            <th>수정일시</th>
        </tr>
		<c:forEach items="${memoList}" var="list">
		    <tr>
            	<td><input type="checkbox" name="listCheck" /></td>
            	<c:if test="${list.MEMO_FAV == 0}"><td>X</td></c:if>
            	<c:if test="${list.MEMO_FAV == 1}"><td>O</td></c:if>
            	<td>
            	<a href="/memoView?memoNum=${list.MEMO_NUM}">${list.MEMO_NAME}</a>
            	</td>
            	<td>${list.USER_ID}</td>
            	<td>${list.MEMO_WRITE_DATE}</td>
           		<td>${list.MEMO_UPDATE_DATE}</td>
           		<td style="display:none">${list.MEMO_NUM}</td>
           		<td style="display:none" id="share_fav">${list.MEMO_SHARE_FAV}</td>
           		<td style="display:none" id="my_fav">${list.MEMO_FAV}</td>
	        </tr>
		</c:forEach>
    </table>
 	<div>
		<c:if test="${page.prev}">
 			<span>[ <a href="/memoListPageSearch?num=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]</span>
		</c:if>
		<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
 			<span>
  				<c:if test="${select != num}">
   					<a href="/memoListPageSearch?num=${num}${page.searchTypeKeyword}">${num}</a>
  				</c:if>    
  
  				<c:if test="${select == num}">
   					<b>${num}</b>
  				</c:if>
 			</span>
		</c:forEach>

		<c:if test="${page.next}">
 			<span>[ <a href="/memoListPageSearch?num=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]</span>
		</c:if>
 	</div>
    <div>
    	<select name="searchType">
    		<option value="title" <c:if test="${page.searchType eq 'title'}">selected</c:if>>제목</option>
    		<option value="context" <c:if test="${page.searchType eq 'content'}">selected</c:if>>내용</option>
    		<option value="user" <c:if test="${page.searchType eq 'writer'}">selected</c:if>>작성자</option>
    	</select>
    	<input type="text" name="keyword" value="${page.keyword}"/>
    	<button class="colorBtn" type="button" id="searchBtn">검색</button> 
    	<button class="colorBtn" type="button" id="writeBtn">작성</button>
    	<button class="colorBtn" type="button" id="shareBtn">공유</button>
    	<button class="colorBtn" type="button" id="favBtn">즐겨찾기</button>
    	<button class="deleteBtn" type="button" id="deleteBtn">삭제</button> 
    </div>
    
<script>
	document.getElementById("searchBtn").onclick = function () {
		let searchType = document.getElementsByName("searchType")[0].value;
		let keyword = document.getElementsByName("keyword")[0].value;
		console.log(searchType)
		console.log(keyword)
		
		location.href = "/memoListPageSearch?num=1" + "&searchType=" + searchType + "&keyword=" + keyword;
	};
	
	document.getElementById("writeBtn").onclick = function () {
		location.href = "/memoWrite"
	};
	
	document.getElementById("shareBtn").onclick = function popup() {
		var tbl = $("#checkboxTestTbl:checkbox:not(:first)");
		console.log("share clicked");
		
		var sessionUser = "${user}";
		
		var checkbox = $('input:checkbox[name=listCheck]:checked');
		var arr = [];
		
		checkbox.each(function(i) {
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			console.log(td.eq(3).text());
			if(sessionUser == td.eq(3).text()) {
				/*  td.eq(6) display:none memoNum */
				var memoId = td.eq(6).text();
				memoId *=1;
				
				arr.push(memoId);
				
			} else {
				alert('메모 제목' + td.eq(2).text() +'의 공유 권한이 없습니다.');
			}
			
		});
		console.log("arr:" +arr);
		var url = "/memoUserPop?arr=" + arr;
		var name = "userPop";
		var popupWidth = 600;
		var popupHeight = 300;
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		var option = 'left=' + popupX + ',top=' + popupY +', width=' + popupWidth+ ',height=' + popupHeight +',menubar=no, status=no, toolbar=no, location=no, scrollbars=yes';
		
		if(arr[0] != null) {
			window.open(url, name, option);
			console.log(arr);
		}

	};
	
	document.getElementById("deleteBtn").onclick = function () {
		var tbl = $("#checkboxTestTbl:checkbox:not(:first)");
		console.log("delete clicked");
		
		var checkbox = $('input:checkbox[name=listCheck]:checked');
		if(confirm("삭제하겠습니까?")){

		checkbox.each(function(i) {
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();
			var cnt = 0;
			
			var memoId = td.eq(6).text();
			memoId *=1;
			var memoUser = td.eq(3).text();
			var sessionUser = "${user}";
			console.log(memoUser);
			if(sessionUser == memoUser || sessionUser == 'admin' ){
				console.log("내 메모 입니다.");
				$.ajax({
					url: "/memoDelete",
					data: "memoNum=" + memoId,
					type: "POST",
					success: function(data){
						var obj = JSON.parse(data);
						if(obj.status == 0) {
							alert("삭제 전 공유를 취소해 주세요.");
						}
						if(cnt == i) {
							location.href = "/memoListPageSearch?num=1";	
						} else {
							cnt++;
						}
					
					},
					error: function(error){
						alert("삭제 실패")	
					}
				})
			
			} else {
				alert("내 메모가 아닙니다.");
			}
		});	
		}
		setTimeout('window.close()', 500);
		
	}
	
  	document.getElementById("favBtn").onclick = function () { 
		var tbl = $("#checkboxTestTbl:checkbox:not(:first)");
		console.log("fav clicked");
		var cnt = 0;
		var checkbox = $('input:checkbox[name=listCheck]:checked');
		if(confirm("즐겨찾기 하시겠습니까?")){

		checkbox.each(function(i) {
				var tr = checkbox.parent().parent().eq(i);
				var td = tr.children();
				var share_fav = td.eq(7).text();
				var my_fav = td.eq(8).text();
				var cnt = 0;
				var fav = td.eq(1).text();
				var sessionUser = "${user}";
				var author = td.eq(3).text()
				var memoId = td.eq(6).text();

				if (fav == "O") {
					if (sessionUser != author) {
						$.ajax({
							url : "/shareMemoCancleFav",
							data : {
								"memoNum" : memoId,
								"userId" : sessionUser
							},
							type : "POST",
							success : function(data) {
								if(cnt == i) {
								alert("성공");
								location.href = "/memoListPageSearch?num=1";
								}
							}

						})
					} else if (sessionUser == author) {
						$.ajax({
							url : "/memoCancleFav",
							data : {
								"memoNum" : memoId
							},
							type : "POST",
							success : function(data) {
								if(cnt == i) {
									alert("성공");
									location.href = "/memoListPageSearch?num=1";
									}
							}

						})
					}
				} else if (fav =="X") {
					if (sessionUser != author) {
						$.ajax({
							url : "/shareMemoAddFav",
							data : {
								"memoNum" : memoId,
								"userId" : sessionUser
							},
							type : "POST",
							success : function(data) {
								if(cnt == i) {
									alert("성공");
									location.href = "/memoListPageSearch?num=1";
									}
							}

						});
					} else if (sessionUser == author) {
						$.ajax({
							url : "/memoAddFav",
							data : {
								"memoNum" : memoId
							},
							type : "POST",
							success : function(data) {
								if(cnt == i) {
									alert("성공");
									location.href = "/memoListPageSearch?num=1";
									}
							}

						});
					}
				}
				
			});
		}
	}
</script>
</body>
</html>