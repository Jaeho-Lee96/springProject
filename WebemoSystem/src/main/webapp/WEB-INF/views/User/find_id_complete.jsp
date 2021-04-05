<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html> 
<head> 
<title>아이디 찾기 성공</title> 
</head> 
<body> 
<p>${ID}</p>
<script> alert(" 찾으시는 ID는 '${ID}'입니다."); 
self.location = "${path}/Login/login"; 
</script> 
</body> 
</html>

