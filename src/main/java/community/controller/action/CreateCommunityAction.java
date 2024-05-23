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
		request.setCharacterEncoding("UTF-8");
		String method = request.getMethod();
		System.out.println("method : " + method);

		CommunityDao communityDao = CommunityDao.getInstance();

		if (method.equalsIgnoreCase("POST")) { // POST 메소드 확인
			StringBuilder jsonBuilder = new StringBuilder();
			String line;

			try (BufferedReader reader = request.getReader()) {
				while ((line = reader.readLine()) != null) {
					jsonBuilder.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}

			String jsonString = jsonBuilder.toString();

			try {
				JSONObject jsonObject = new JSONObject(jsonString);
				String userId = jsonObject.getString("userId");
				String userNickname = jsonObject.getString("userNickname");
				String title = jsonObject.getString("title");
				String content = jsonObject.getString("content");
				String category = jsonObject.getString("category");

				CommunityRequestDto community = new CommunityRequestDto(userId, userNickname, title, content, category);
				communityDao.createCommunity(community);

				System.out.println(communityDao);

				// 결과 응답
				JSONObject resObj = new JSONObject();
				resObj.put("status", 200);
				resObj.put("message", "게시물 등록 완료");

				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().append(resObj.toString());

			} catch (org.json.JSONException e) {
				e.printStackTrace();
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json;charset=UTF-8");

				JSONObject resObj = new JSONObject();
				resObj.put("status", 400);
				resObj.put("message", "잘못된 JSON 형식입니다.");

				response.getWriter().append(resObj.toString());
			}
		} else {
			// 잘못된 메소드 요청에 대한 처리
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");

			JSONObject resObj = new JSONObject();
			resObj.put("status", 405);
			resObj.put("message", "허용되지 않는 메소드입니다.");

			response.getWriter().append(resObj.toString());
		}
	}
}