<%@page import="user.model.UserResponseDto"%>
<%@page import="user.model.UserDao"%>
<%@page import="user.model.UserRequestDto"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		boolean isValid = true;
		
		if(id == null || id.equals(""))
			isValid = false;
		else if(password == null || password.equals(""))
			isValid = false;
		
		if(isValid) {

			UserDao userDao = UserDao.getInstance();
			UserResponseDto user = userDao.findUserByIdAndPassword(id);
			
			if(user != null) {
				session.setAttribute("user", user);
				response.sendRedirect("/mypage");				
			} else {
				response.sendRedirect("/login");				
			}
		} else {
			response.sendRedirect("/login");
		}
	%>
</body>
</html>