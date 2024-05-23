package community.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import community.model.CommunityDao;
import community.model.CommunityRequestDto;
import util.Action;

public class DeleteCommunityAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CommunityDao communityDao = CommunityDao.getInstance();
		
		String communityCode = request.getParameter("code");
		int code = Integer.parseInt(communityCode);
		String userId = request.getParameter("userId");
		
		CommunityRequestDto community = new CommunityRequestDto(code, userId);
		boolean success = communityDao.deleteCommunity(community);
		
		JSONObject resObj = new JSONObject();
		
		if(success) {
			resObj.put("status", 200);
			resObj.put("message", "게시물이 삭제되었습니다.");
		} else {
			resObj.put("status", 500);
			resObj.put("message", "게시물 삭제에 실패했습니다.");
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().append(resObj.toString());
	}
}
