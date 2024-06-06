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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        request.setCharacterEncoding("UTF-8");
        String method = request.getMethod();

        CommunityDao communityDao = CommunityDao.getInstance();

        if (method.equalsIgnoreCase("POST")) {
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