package diaryComment.controller.action;

import diaryComment.model.DiaryCommentDao;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDiaryComment implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        DiaryCommentDao diaryCommentDao = DiaryCommentDao.getInstance();

        String method = request.getMethod();
        String code = null;

        if(method.equals("DELETE")) {
            code = request.getParameter("code");

            if(code != null && !code.isEmpty()) {
                boolean isDeleted = diaryCommentDao.deleteComment(code);

                if(isDeleted) {
                    resObj.put("status", 200);
                    resObj.put("message", "댓글 삭제 완료");
                }else{
                    resObj.put("status", 400);
                    resObj.put("message" , "댓글 삭제 실패");
                }
            }else{
                resObj.put("status", 400);
                resObj.put("message" , "잘못된 요청");
            }
        }else{
            resObj.put("status", 404);
            resObj.put("message" , "잘못된 요청");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().append(resObj.toString());
    }
}
