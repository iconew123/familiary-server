package baby.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import baby.model.BabyDao;
import baby.model.BabyRequestDto;
import util.Action;

public class BabyUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인 허용
		response.setHeader("Access-Control-Allosw-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");


		String baby_code = request.getParameter("code");
		String nickname = request.getParameter("nickname");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String expected_date = request.getParameter("expected_date");
		String blood_type = request.getParameter("blood_type");

		System.out.println(nickname);

		BabyDao dao = new BabyDao();
		BabyRequestDto baby = new BabyRequestDto(baby_code, nickname, name, gender, expected_date, blood_type);
		boolean success = dao.updateBaby(baby);

		// 응답 JSON 생성
		JSONObject jsonResponse = new JSONObject();
		if (success) {
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "아기 정보가 성공적으로 업데이트되었습니다.");
		} else {
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "아기 정보 업데이트에 실패했습니다.");
		}
		
        // 클라이언트로 응답 전송ㅋㅋ
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(jsonResponse.toString());

	}

}
