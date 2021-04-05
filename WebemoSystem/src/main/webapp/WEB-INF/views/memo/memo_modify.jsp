<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메모 수정</title>
<script src = "${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
<script src ="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
</head>
	<body>
	<form action="memoUpdate?memoNum=${memo.memoNum}" method="post">
		<input name="memoName" type="text" value="${memo.memoName}"/>
		<hr>
		<textarea name="memoContext">${memo.memoContext}</textarea>
		<script>CKEDITOR.replace("memoContext")</script>
		<input type="submit" value="작성">
	 	</form>
	 		<div>
			<select name ="formType" id="formType">
				<option value="양식 선택">----</option>
				<c:forEach items="${forms}" var ="form" varStatus="status">
					<option value="${form.FORM_DATA}">${form.FORM_NAME}</option>
				</c:forEach>
			</select>
			<button id="insertBtn">양식 추가</button>
	</div> 	
	</body>
	<script>
	$(document).ready(function(){
		var btn = $("#insertBtn");
		
		btn.click(function() {
			var textArea =  document.getElementsByName('memoContext');
			var textValue = CKEDITOR.instances.memoContext.getData();
			var selected = $("#formType option:selected").val();
			console.log(selected)
						
 			var addText = selected;
			
 			CKEDITOR.instances.memoContext.setData(textValue +  addText);
 			
			
		})
		
	})
	</script>
</html>