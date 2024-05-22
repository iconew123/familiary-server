<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="/resources/style/user.css">
<meta charset="UTF-8">
</head>
<jsp:include page="/header"></jsp:include>
<body>

	<button onclick="location.href='/updateUser'">회원정보수정</button>
	<button onclick="location.href='/deleteUser'">회원탈퇴</button>
	<button onclick="location.href='/deleteUser'">아기정보</button>
</body>

</html>