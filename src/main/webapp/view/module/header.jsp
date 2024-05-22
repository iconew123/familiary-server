<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon"
	href="https://em-content.zobj.net/source/twitter/376/skateboard_1f6f9.png">
<link rel="stylesheet" href="/resources/style/grid.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<header>
		<h1>Familiary</h1>
		
		<c:choose>
			<c:when test="${empty user }">
				<button onclick="location.href='/main'">로그아웃</button>
			</c:when>
			<c:otherwise>
				<button onclick="location.href='/login'">로그인</button>
			</c:otherwise>
		</c:choose>
		<button onclick="location.href='/join'">회원가입</button>

         <button onclick="location.href='/diaryHome'">다이어리</button>
         <button onclick="location.href='/info'">INFO:</button>
         <button onclick="location.href='/community'">커뮤니티</button>
         <button onclick="location.href='/myPage'">MY PAGE</button>

	</header>
</body>
</html>