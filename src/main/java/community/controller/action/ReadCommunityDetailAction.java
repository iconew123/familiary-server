package community.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import community.model.CommunityDao;
import community.model.CommunityResponseDto;
import util.Action;

public class ReadCommunityDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

		String communityCode = request.getParameter("code");

		CommunityDao communityDao = CommunityDao.getInstance();
		int code = Integer.parseInt(communityCode);

		CommunityResponseDto community = communityDao.findCommunityByCode(code);

		JSONObject resObj = new JSONObject();
		if (community != null) {
			resObj.put("status", 200);
			resObj.put("message", "게시물 조회 성공");

			JSONObject communityJson = new JSONObject();
			communityJson.put("code", community.getCode());
			communityJson.put("userNickname", community.getUserNickname());
			communityJson.put("title", community.getTitle());
			communityJson.put("content", community.getContent());

			resObj.put("community", communityJson);
		} else {
			resObj.put("status", 404);
			resObj.put("message", "게시물을 찾을 수 없습니다.");
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}
}
