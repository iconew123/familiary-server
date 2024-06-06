package community.controller.action;

import community.model.CommunityDao;
import community.model.CommunityResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainCommunityAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        CommunityDao communityDao = CommunityDao.getInstance();
        List<CommunityResponseDto> mainCommunityList = communityDao.findCommunityAll();

        JSONArray jsonArray = new JSONArray();
        for (CommunityResponseDto dto : mainCommunityList) {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("code", dto.getCode());
            jsonObject.put("userId", dto.getUserId());
            jsonObject.put("userNickName", dto.getUserNickname());
            jsonObject.put("title", dto.getTitle());
            jsonObject.put("content", dto.getContent());
            jsonObject.put("category", dto.getCategory());
            jsonObject.put("regDate", dto.getRegDate().toString());

            jsonArray.put(jsonObject);
        }
        String jsonString = jsonArray.toString();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }
}