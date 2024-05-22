package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class JoinActionPro implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String name = request.getParameter("name");
		String security_number = request.getParameter("security_number");
		String telecom = request.getParameter("telecom");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String adress = request.getParameter("adress");

		UserRequestDto userDto = new UserRequestDto(id, password, nickname, name, security_number, telecom, phone,
				email, adress);

		UserDao userDao = UserDao.getInstance();
		UserResponseDto user = userDao.createUser(userDto);

		if (user == null) {
			response.sendRedirect("/join");
		} else {
			System.out.println("user : " + user);

			response.sendRedirect("/login");
		}

	}
}
