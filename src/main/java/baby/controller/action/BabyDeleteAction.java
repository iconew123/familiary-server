package baby.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import baby.model.Baby;
import baby.model.BabyDao;
import baby.model.BabyRequestDto;
import util.Action;

public class BabyDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인 허용
		response.setHeader("Access-Control-Allosw-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

		String baby_code = request.getParameter("code");
		BabyDao dao = new BabyDao();
		Baby baby = dao.findBabyByCode(baby_code);
		
		JSONObject jsonResponse = new JSONObject();
		boolean success = dao.deleteBaby(baby);
		if(success) {
			jsonResponse.put("status", 200);
			jsonResponse.put("message", "아기 정보가 성공적으로 삭제되었습니다.");
		} else {
			jsonResponse.put("status", 500);
			jsonResponse.put("message", "아기 정보 삭제에 실패했습니다.");
		}
		
		// 클라이언트로 응답 전송.
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(jsonResponse.toString());
	}

}
