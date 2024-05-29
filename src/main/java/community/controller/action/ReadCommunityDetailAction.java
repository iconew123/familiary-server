package community.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import community.model.CommunityDao;
import community.model.CommunityResponseDto;
import util.Action;

public class ReadCommunityDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String communityCode = request.getParameter("code");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("userId");

		CommunityDao communityDao = CommunityDao.getInstance();
		int code = Integer.parseInt(communityCode);

		CommunityResponseDto community = communityDao.findCommunityByCode(code);

		request.setAttribute("userId", id);
		request.setAttribute("community", community);

		JSONObject resObj = new JSONObject();
		if (community != null) {
			resObj.put("status", 200);
			resObj.put("message", "게시물 조회 성공");

			// CommunityResponseDto를 JSONObject로 변환하여 추가
			JSONObject communityJson = new JSONObject();
			communityJson.put("code", community.getCode());
			communityJson.put("userId", community.getUserId());
			communityJson.put("userNickname", community.getUserNickname());
			communityJson.put("title", community.getTitle());
			communityJson.put("content", community.getContent());
			communityJson.put("category", community.getCategory());

			// 필요한 속성들을 모두 추가
			resObj.put("community", communityJson);
		} else {
			resObj.put("status", 404);
			resObj.put("message", "게시물을 찾을 수 없습니다.");
		}

		response.getWriter().append(resObj.toString());
	}
}
