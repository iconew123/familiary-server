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

public class UpdateCommunityAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;

		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		String requestBody = sb.toString();

		try {
			JSONObject json = new JSONObject(requestBody);

			int code = json.getInt("code");
			String userNickname = json.getString("userNickname");
			String title = json.getString("title");
			String content = json.getString("content");
			String category = json.getString("category");

			if (title.isEmpty() || content.isEmpty() || category.isEmpty()) {
				throw new IllegalArgumentException("모든 필드를 입력해야 합니다.");
			}

			CommunityRequestDto community = new CommunityRequestDto(code, userNickname, title, content, category);

			CommunityDao communityDao = CommunityDao.getInstance();
			communityDao.updateCommunity(community);

			JSONObject resObj = new JSONObject();
			resObj.put("status", 200);
			resObj.put("message", "게시물 수정 완료");
			resObj.put("community", new JSONObject(community));

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(resObj.toString());
		} catch (Exception e) {
			JSONObject resObj = new JSONObject();
			resObj.put("status", 400);
			resObj.put("message", "게시물 수정 중 오류 발생: " + e.getMessage());

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(resObj.toString());
		}
	}
}