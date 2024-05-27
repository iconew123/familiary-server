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
        request.setCharacterEncoding("UTF-8");
        String method = request.getMethod();
        System.out.println("method : " + method);

        CommunityCommentDao communityCommentDao = CommunityCommentDao.getInstance();

        if (method.equalsIgnoreCase("POST")) { // POST 메소드 확인
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
                int code = jsonObject.getInt("communityCode");
                String userId = jsonObject.getString("userId");
                String userNickname = jsonObject.getString("userNickname");
                String content = jsonObject.getString("content");

                CommunityCommentRequestDto communityComment = new CommunityCommentRequestDto(code, userId, userNickname, content);
                communityCommentDao.createCommunityComment(communityComment);

                System.out.println("Community comment created: " + communityComment);

                // 결과 응답
                JSONObject resObj = new JSONObject();
                resObj.put("status", 200);
                resObj.put("message", "댓글 등록 완료");

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
            // 잘못된 메소드 요청에 대한 처리
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