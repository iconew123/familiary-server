package community.controller.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import community.model.CommunityDao;
import community.model.CommunityRequestDto;
import util.Action;
import util.InputStreamParsor;

public class CreateCommunityAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        request.setCharacterEncoding("UTF-8");
        String method = request.getMethod();

        if (!method.equalsIgnoreCase("POST")) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            JSONObject resObj = new JSONObject();
            resObj.put("status", 405);
            resObj.put("message", "허용되지 않는 메소드입니다.");
            response.getWriter().append(resObj.toString());
            return;
        }

        CommunityDao communityDao = CommunityDao.getInstance();
        String imageId = null;
        String imageUrl = null;

        try {
            Collection<Part> parts = request.getParts();
            String userId = null, userNickname = null, title = null, content = null, category = null;

            for (Part part : parts) {
                String partName = part.getName();
                if (partName.equals("photo") && part.getContentType() != null) {
                    InputStream in = part.getInputStream();
                    try {
                        JSONObject jsonResponse = InputStreamParsor.uploadImage(in, part.getContentType());
                        imageId = jsonResponse.getJSONObject("data").getString("id");
                        imageUrl = jsonResponse.getJSONObject("data").getString("url");
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServletException("이미지 업로드 실패", e);
                    }
                } else {
                    InputStream inputStream = part.getInputStream();
                    String value = new String(inputStream.readAllBytes(), "UTF-8");
                    switch (partName) {
                        case "userId":
                            userId = value;
                            break;
                        case "userNickname":
                            userNickname = value;
                            break;
                        case "title":
                            title = value;
                            break;
                        case "content":
                            content = value;
                            break;
                        case "category":
                            category = value;
                            break;
                    }
                }
            }

            if (userId == null || userNickname == null || title == null || content == null || category == null) {
                throw new ServletException("필수 데이터가 누락되었습니다.");
            }

            CommunityRequestDto community = new CommunityRequestDto(userId, userNickname, title, content, category);
            communityDao.createCommunity(community);

            if (imageId != null && imageUrl != null) {
                // 이미지 처리 로직 추가
            }

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
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            JSONObject resObj = new JSONObject();
            resObj.put("status", 500);
            resObj.put("message", "서버 에러가 발생했습니다.");
            response.getWriter().append(resObj.toString());
        }
    }
}