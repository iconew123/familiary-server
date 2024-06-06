package communityComment.controller.action;

import communityComment.model.CommunityCommentDao;
import communityComment.model.CommunityCommentRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateCommunityCommentAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        request.setCharacterEncoding("UTF-8");
        String method = request.getMethod();

        CommunityCommentDao communityCommentDao = CommunityCommentDao.getInstance();

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
                int communityCode = jsonObject.getInt("code");
                String userId = jsonObject.getString("userId");
                String userNickname = jsonObject.getString("userNickname");
                String content = jsonObject.getString("content");

                CommunityCommentRequestDto communityComment = new CommunityCommentRequestDto(communityCode, userId, userNickname, content);
                communityCommentDao.createCommunityComment(communityComment);

                JSONObject resObj = new JSONObject();
                resObj.put("status", 200);
                resObj.put("message", "댓글 등록 완료");
                resObj.put("comment", communityComment);

                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.append(resObj.toString());
                    out.flush();
                }

            } catch (org.json.JSONException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");

                JSONObject resObj = new JSONObject();
                resObj.put("status", 400);
                resObj.put("message", "잘못된 JSON 형식입니다.");

                try (PrintWriter out = response.getWriter()) {
                    out.append(resObj.toString());
                    out.flush();
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");

            JSONObject resObj = new JSONObject();
            resObj.put("status", 405);
            resObj.put("message", "허용되지 않는 메소드입니다.");

            try (PrintWriter out = response.getWriter()) {
                out.append(resObj.toString());
                out.flush();
            }
        }
    }
}