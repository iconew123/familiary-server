package community.controller.action;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import community.model.CommunityDao;
import community.model.CommunityRequestDto;
import util.Action;

public class CreateCommunityAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인 허용
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

		request.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		System.out.println("method : " + method);

		CommunityDao communityDao = CommunityDao.getInstance();

		String userId = request.getParameter("userId");
		String userNickname = request.getParameter("userNickname");
		System.out.println(userId);
		System.out.println(userNickname);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(content);
		String category = request.getParameter("category");

		if (userId != null && userNickname != null && title != null && content != null && category != null) {
			CommunityRequestDto community = new CommunityRequestDto(userId, userNickname, title, content, category);
			communityDao.createCommunity(community);

			// 결과 응답
			JSONObject responseObject = new JSONObject();
			responseObject.put("status", 200);
			responseObject.put("message", "게시물 등록 완료");
			responseObject.put("community", new JSONObject(community)); // CommunityRequestDto를 JSONObject로 변환하여 넣음

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(responseObject.toString());
		} else {
			// 잘못된 요청에 대한 처리
			JSONObject errorResponse = new JSONObject();
			errorResponse.put("status", 400);
			errorResponse.put("message", "잘못된 요청입니다.");

			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(errorResponse.toString());
		}
	}
}