package community.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import community.model.CommunityDao;
import community.model.CommunityResponseDto;
import org.json.JSONObject;
import util.Action;

public class ReadCommunityByRecommendAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CommunityDao communityDao = CommunityDao.getInstance();
		List<CommunityResponseDto> boardList = communityDao.findCommunityAllByRecommend();

		JSONArray jsonArray = new JSONArray();
		for (CommunityResponseDto dto : boardList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", dto.getCode());
			jsonObject.put("userId", dto.getUserId());
			jsonObject.put("userNickName", dto.getUserNickname());
			jsonObject.put("title", dto.getTitle());
			jsonObject.put("content", dto.getContent());
			jsonObject.put("category", dto.getCategory());
			jsonObject.put("regDate", dto.getRegDate().toString()); // Assuming regDate is a string

			jsonArray.put(jsonObject);
		}

		String jsonString = jsonArray.toString();

		System.out.println(jsonString);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jsonString);
	}
}
