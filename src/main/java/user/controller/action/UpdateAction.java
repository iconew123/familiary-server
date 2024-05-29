package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.Action;

public class UpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
		String method = request.getMethod();


		if (method.equals("POST")) {

			String password = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
			String newNickname = request.getParameter("nickname");
			String newTelecom = request.getParameter("telecom");
			String newPhone = request.getParameter("phone");
			String newEmail = request.getParameter("email");
			String newAdress = request.getParameter("adress");

			boolean isValid = true;

			if (password == null || password.equals(""))
				isValid = false;
			else if (newPassword == null || newPassword.equals(""))
				isValid = false;

			JSONObject resObj = new JSONObject();

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");

			if (isValid) {
				UserDao userDao = UserDao.getInstance();
				UserRequestDto userDto = new UserRequestDto();

				HttpSession session = request.getSession();
				UserResponseDto user = (UserResponseDto) session.getAttribute("user");


				if (!newPassword.equals("") && !newPassword.equals(password)) {
					user = userDao.updateUserPassword(userDto, newPassword);
				}
				if (!newNickname.equals("") && !newNickname.equals(userDto.getNickname())) {
					user = userDao.updateUserNickname(userDto, newPassword);
				}
				if (!newEmail.equals(userDto.getEmail())) {
					user = userDao.updateUserEmail(userDto, newEmail);
				}

				if (!newPhone.equals(userDto.getPhone()) || !newTelecom.equals(userDto.getTelecom())) {
					user = userDao.updateUserPhone(userDto, newTelecom, newPhone);
					resObj.put("telecom", newTelecom);
					resObj.put("phone", newPhone);
				}

				if (!newAdress.equals("") && !newAdress.equals(userDto.getAdress())) {
					user = userDao.updateUserAdress(userDto, newPassword);
				}

			}

		}

	}

}