package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class JoinAction implements UserAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getMethod();
		System.out.println("method : " + method);

		if (method.equals("POST")) {
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
			JSONObject resObj = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf8");
			if (user != null) {

				if (isVaild) {
					resObj.put("status", 200);
					resObj.put("message", "다이어리가 성공적으로 등록되었습니다.");
					resObj.put("id", id);
					resObj.put("password", password);
					resObj.put("nickname", nickname);
					resObj.put("security_number", security_number);
					resObj.put("telecom", telecom);
					resObj.put("phone", phone);
					resObj.put("email", email);
					resObj.put("adress", adress);
					response.getWriter().append(resObj.toString());
				} else {
					resObj.put("status", 404);
					resObj.put("message", "다이어리 등록 실패");

					response.getWriter().append(resObj.toString());
				}
			}
		} else {
			response.sendError(404, "유효하지 않은 접근 경로입니다.");
		}

	}
}
