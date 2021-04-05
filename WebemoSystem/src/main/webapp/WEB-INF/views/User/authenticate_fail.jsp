<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html> 
<head> 
<title>인증 실패</title> 
</head> 
<body> 
<script> alert("현재 비밀번호를 다시 	확인해주세요."); 
self.location = "${path}/User/authenticate"; 
</script> 
</body> 
</html>

