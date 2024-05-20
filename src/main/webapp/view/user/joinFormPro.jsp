<%@page import="user.model.UserResponseDto"%>
<%@page import="user.model.UserDao"%>
<%@page import="user.model.UserRequestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String nickname = request.getParameter("nickname");
	String name = request.getParameter("name");
	String security_number = request.getParameter("security_number");
	String telecom = request.getParameter("telecom");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");
	String adress = request.getParameter("adress");

	boolean isValid = true;

	if (id == null || id.equals(""))
		isValid = false;
	else if (password == null || password.equals(""))
		isValid = false;
	else if (nickname == null || nickname.equals(""))
		isValid = false;
	else if (name == null || name.equals(""))
		isValid = false;
	else if (security_number == null || security_number.equals(""))
		isValid = false;
	else if (telecom == null || telecom.equals(""))
		isValid = false;
	else if (phone == null || phone.equals(""))
		isValid = false;

	if (isValid) {
		UserRequestDto userDto = new UserRequestDto(id, password, nickname, name, security_number, telecom, phone, email,
		adress);

		UserDao userDao = UserDao.getInstance();
		UserResponseDto user = userDao.createUser(userDto);

		if (user == null) {
			response.sendRedirect("/join");
		} else {
			System.out.println("user : " + user);

			response.sendRedirect("/login");
		}
	} else
		response.sendRedirect("/join");
	%>
</body>
</html>