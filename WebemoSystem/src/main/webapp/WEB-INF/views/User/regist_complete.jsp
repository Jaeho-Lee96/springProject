<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html> 
<head> 
<title>회원가입 완료</title> 
</head> 
<body> 
<p>${ID}</p>
<script> alert(" 회원가입이 완료되었습니다. 가입하신 정보로 로그인해주세요"); 
self.location = "${path}/Login/login"; 
</script> 
</body> 
</html>

