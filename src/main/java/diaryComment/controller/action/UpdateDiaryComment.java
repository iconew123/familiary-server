package diaryComment.controller.action;

import diary.model.DiaryRequestDto;
import diaryComment.model.DiaryCommentDao;
import diaryComment.model.DiaryCommentResponseDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class UpdateDiaryComment implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        DiaryCommentDao diaryCommentDao = DiaryCommentDao.getInstance();

        String method = request.getMethod();
        String code = null;
        String content = null;

        if(method.equals("POST")){
            code = request.getParameter("code");
            content = request.getParameter("content");

            boolean isValid = true;
            if(code == null || code.isEmpty())
                isValid = false;
            if(content == null || content.isEmpty())
                isValid = false;

            if(isValid){
                DiaryCommentResponseDto comment = diaryCommentDao.findComment(code);

                if(comment != null){
                    boolean update = diaryCommentDao.updateComment(comment.getCode(), content);

                    if(update){
                        resObj.put("status", 200);
                        resObj.put("message" , "댓글 수정 성공");
                    }else{
                        resObj.put("status", 400);
                        resObj.put("message" , "댓글 수정 실패");
                    }
                }else{
                    resObj.put("status", 400);
                    resObj.put("message" , "댓글을 찾을 수 없습니다.");
                }
            }else{
                resObj.put("status", 400);
                resObj.put("message", "요소가 누락됐습니다.");
            }

        }else{
            resObj.put("status" , 404);
            resObj.put("message" , "요청방법을 확인해주세요");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().append(resObj.toString());
    }
}
