package user.controller.action;

import org.json.JSONObject;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
		String method = request.getMethod();

		if (method.equals("POST")) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
			String newNickname = request.getParameter("newNickname");
			String newTelecom = request.getParameter("newTelecom");
			String newPhone = request.getParameter("newPhone");
			String newEmail = request.getParameter("newEmail");
			String newAddress = request.getParameter("newAddress");

			boolean isValid = true;

			if (password == null || password.equals(""))
				isValid = false;
			else if (newPassword == null || newPassword.equals(""))
				isValid = false;

			if (isValid) {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=utf-8");
				JSONObject resObj = new JSONObject();

				UserDao userDao = UserDao.getInstance();

				UserResponseDto user = userDao.findUserByIdAndPassword(id,password);
				UserRequestDto userDto = new UserRequestDto();



				System.out.println(user.getNickname());
				System.out.println(newNickname);

					if (!newPassword.equals(password)) {
						user = userDao.updateUserPassword(userDto, newPassword);
					}
					if (!newNickname.equals(user.getNickname())) {
						userDto.setEmail(newNickname);
						user = userDao.updateUserNickname(userDto);
					}
					if (!newEmail.equals(user.getEmail())) {
						userDto.setEmail(newEmail);
						user = userDao.updateUserNickname(userDto);
					}
					if (!newPhone.equals(user.getPhone()) || !newTelecom.equals(user.getTelecom())) {
						userDto.setEmail(newPhone);
						userDto.setEmail(newTelecom);
						user = userDao.updateUserPhone(userDto);
					}
					if (!newAddress.equals(user)) {
						userDto.setEmail(newAddress);
						user = userDao.updateUserAdress(userDto);
					}

					if (user!=null) {

						resObj.put("status", 200);
						resObj.put("message", "User updated successfully.");
						resObj.put("id", user.getId());
						resObj.put("password", newPassword);
						resObj.put("nickname", user.getNickname());
						resObj.put("name", user.getName());
						resObj.put("securityNumber", user.getSecurityNumber());
						resObj.put("telecom", user.getTelecom());
						resObj.put("phone", user.getPhone());
						resObj.put("adress", user.getAdress());
						resObj.put("email", user.getEmail());
					} else {
						resObj.put("status", 500);
						resObj.put("message", "Failed to update user.");
					}
				}
			}

		}
	}
