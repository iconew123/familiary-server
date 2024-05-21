<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>

	<button onclick="location.href='/updateUser'">회원정보수정</button>
	<button onclick="location.href='/deleteUser'">회원탈퇴</button>
	<button onclick="location.href='/deleteUser'">아기정보</button>
</body>
<jsp:include page="/footer" />
</html>