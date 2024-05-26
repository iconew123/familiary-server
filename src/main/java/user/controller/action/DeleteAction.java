package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import baby.model.Baby;
import baby.model.BabyDao;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();

		if (method.equals("DELETE")) {

			String password = request.getParameter("password");
			boolean isValid = true;

			if (password == null || password.equals(""))
				isValid = false;

			JSONObject resObj = new JSONObject();

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");

			if (isValid) {
				UserDao userDao = UserDao.getInstance();
				HttpSession session = request.getSession();
				UserResponseDto user = (UserResponseDto) session.getAttribute("user");
				String id = user.getId();

				UserRequestDto userDto = new UserRequestDto();

				userDto.setId(id);
				userDto.setPassword(password);

				boolean result = userDao.deleteUser(userDto);

				if (result) {
					resObj.put("status", 200);
					resObj.put("message", "User deleted successfully.");

				} else {
					resObj.put("status", 400);
					resObj.put("message", "No Existed User");
				}

			}

		}
	}

}