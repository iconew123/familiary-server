package user.controller.action;

import org.json.JSONObject;
import user.model.UserDao;
import user.model.UserRequestDto;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

		String method = request.getMethod();
		if (method.equals("DELETE")) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			boolean isValid = true;
			System.out.println("id : " + id);
			System.out.println("password : " + password);


			if (password == null || password.equals(""))
				isValid = false;

			JSONObject resObj = new JSONObject();

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			System.out.println(password);
			if (isValid) {
				UserDao userDao = UserDao.getInstance();

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