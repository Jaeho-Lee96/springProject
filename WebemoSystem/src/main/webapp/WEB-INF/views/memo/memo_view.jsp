<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 조회</title>
<script src ="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<style>
body {
	padding-top: 70px;
	padding-bottom: 30px;
}

.memo_title {
	font-weight : 700;
	font-size : 22pt;
	margin : 10pt;
}

.memo_info_box {
	color : #6B6B6B;
	margin : 10pt;
}

.memo_author {
	font-size : 10pt;
	margin-right : 10pt;

}
.memo_date {

	font-size : 10pt;
}

.memo_content {

	color : #444343;

	font-size : 12pt;

	margin : 10pt;
	
	padding-bottom: 10pt;
}
.memo_tag {
	font-size : 11pt;
	margin : 10pt;
	padding-bottom : 10pt;
}

hr {
	height: 1px;
	background-color: #0081cc;
}

.btn_color {
	background-color: #0081cc;
	color:white;
	border:0;
	outline:0;
}
</style>
<script src = "${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script>
	function memoUpdate(memoNum) {
		if("${user}" == "${memo.userId}") {
			location.href='/memoUpdate?memoNum=' + memoNum;
		} else {
			alert("수정할 수 있는 권한이 없습니다.");
		}
	}
	
	function memoDelete(memoNum) {
		if("${user}" == "${memo.userId}") {
			if(confirm("삭제하시겠습니까?")) {
				$.ajax({
					url: "/memoDelete",
					data: "memoNum=" + memoNum,
					type: "POST",
					success: function(data){
						var obj = JSON.parse(data);
						if(obj.status == 0) {
							alert("삭제 전 공유를 취소해 주세요.");
						}
					},
					error: function(error){
						alert("삭제 실패");
					}
				})
				/* location.href='/memoDelete?memoNum=' + memoNum; */
			}
		} else {
			alert("삭제할 수 있는 권한이 없습니다.");
		}
	}
	
	function cancelShare(memoNum) {
		if("${user}" == "${memo.userId}") {
			var child;
			var url = "/memoShareCanclePop?memoNum=" + ${memo.memoNum};
			var name = "canclPop";
			var popupWidth = 600;
			var popupHeight = 300;
			var popupX = (window.screen.width / 2) - (popupWidth / 2);
			var popupY= (window.screen.height / 2) - (popupHeight / 2);
			var option = 'left=' + popupX + ',top=' + popupY +', width=' + popupWidth+ ',height=' + popupHeight +',menubar=no, status=no, toolbar=no, location=no, scrollbars=yes';
			
			child = window.open(url, name, option);
			
			
		} else { alert("공유 취소할 수 있는 권한이 없습니다."); }
	
	}
	function saveToFile_Chrome(fileName, content) {
		content = content.replace(/(<([^>]+)>)/ig,"");
		var blob = new Blob([content], { type: 'text/plain' });
		objURL = window.URL.createObjectURL(blob);
		            
		// 이전에 생성된 메모리 해제
		if (window.__Xr_objURL_forCreatingFile__) {
		    window.URL.revokeObjectURL(window.__Xr_objURL_forCreatingFile__);
		}
		window.__Xr_objURL_forCreatingFile__ = objURL;
		var a = document.createElement('a');
		a.download = fileName;
		a.href = objURL;
		a.click();
		}
	
	function downloadBtn() {
		saveToFile_Chrome("${memo.memoName}", "${memo.memoContext}")
	}
	
</script>
</head>
	<body>
	<div style"> 
		<button class="btn_color" onclick="location='/Common/main'">메인으로</button>
		<button class="btn_color" onclick="downloadBtn()">다운로드</button>
	</div>
	<form action="memoWrite" method="post">
		<article>
		<div class="container" role="main">
			
			<div class="bg-white rounded shadow-sm">
				<div class="memo_title"><c:out value="${memo.memoName}"/></div>
				<div class="memo_info_box">
					<span class="memo_author"><c:out value="작성자: ${memo.userId}"/>,</span>
					<span class="memo_date"><c:out value="등록일시: ${memo.memoWriteDate}"/></span>
					<span class="memo_date"><c:out value="수정일시: ${memo.memoUpdateDate}"/></span>
					<span class="memo_date"><c:out value="공유일시: ${memo.memoShareDate}"/></span>
				</div>
				<hr>
				<div class="board_content">${memo.memoContext}</div>
			</div>
						
		</div>
			
		</article>
	</form>
	<div style= "margin-top: 20px">
	<button class="btn_color" onclick="memoUpdate(${memo.memoNum});">수정</button>
	<button class="btn_color" onclick="memoDelete(${memo.memoNum});">삭제</button>
	<button class="btn_color" onclick="cancelShare(${memo.memoNum});">공유 취소</button>
	</div>
	
	</body>

</html>