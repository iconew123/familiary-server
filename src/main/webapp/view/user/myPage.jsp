<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/header" />
</head>
<body>

	<button onclick="location.href='/updateUser'">ȸ����������</button>
	<button onclick="location.href='/deleteUser'">ȸ��Ż��</button>
	<button onclick="location.href='/deleteUser'">�Ʊ�����</button>
</body>
<jsp:include page="/footer" />
</html>