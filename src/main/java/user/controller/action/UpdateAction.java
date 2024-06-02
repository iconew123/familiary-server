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
			if (newPassword == null || newPassword.equals(""))
				isValid = false;

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			JSONObject resObj = new JSONObject();

			System.out.println(isValid);

			if (isValid) {
				UserDao userDao = UserDao.getInstance();
				UserResponseDto user = userDao.findUserByIdAndPassword(id,password);
				UserRequestDto userDto = new UserRequestDto();
				userDto.setId(user.getId());
				userDto.setPassword(password);


				if(!password.equals(newPassword)) {
					user = userDao.updateUserPassword(userDto, newPassword);
					userDto.setPassword(newPassword);
				}


				if (!newNickname.equals(user.getNickname())) {
					userDto.setNickname(newNickname);
					user = userDao.updateUserNickname(userDto);
				}

				if (!newEmail.equals(user.getEmail())) {
					userDto.setEmail(newEmail);
					user = userDao.updateUserEmail(userDto);
				}

				if (!newPhone.equals(user.getPhone()) || !newTelecom.equals(user.getTelecom())) {
					userDto.setPhone(newPhone);
					userDto.setTelecom(newTelecom);
					user = userDao.updateUserPhone(userDto);

				}
				if (!newAddress.equals(user)) {
					userDto.setAdress(newAddress);
					user = userDao.updateUserAdress(userDto);
				}



				System.out.println("user : " + user.getId());
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
					resObj.put("email", user.getEmail());
					resObj.put("adress", user.getAdress());

				} else {
					response.sendError(400);
					resObj.put("status", 400);
					resObj.put("message", "No Existed User");
				}

			} else {
				response.sendError(500);
				resObj.put("status", 500);
				resObj.put("message", "Database Error");
			}
			response.getWriter().append(resObj.toString());

			}


		}
	}
