package community.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import community.model.CommunityDao;
import community.model.CommunityResponseDto;
import util.Action;

public class ReadCommunityByRecommendAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CommunityDao communityDao = CommunityDao.getInstance();
		List<CommunityResponseDto> boardList = communityDao.findCommunityAllByRecommend();

		JSONArray resObj = new JSONArray(boardList);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		response.getWriter().append(resObj.toString());
	}
}
