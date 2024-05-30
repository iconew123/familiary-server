package community.controller.action;

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
		response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인 허용
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

		String communityCode = request.getParameter("code");
		int code = Integer.parseInt(communityCode);
		
		String userId = request.getParameter("userId");
		String userNickname = request.getParameter("userNickname");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String category = request.getParameter("category");
		
		CommunityDao communityDao = CommunityDao.getInstance();
		CommunityRequestDto community = new CommunityRequestDto(code, userId, userNickname, title, content, category);
		
		communityDao.updateCommunity(community);
		
		JSONObject resObj = new JSONObject(community);
		resObj.put("status", 200);
		resObj.put("message", "게시물 수정 완료");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}
}
