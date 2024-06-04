package diaryComment.controller.action;

import diaryComment.model.DiaryCommentDao;
import diaryComment.model.DiaryCommentRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateDiaryComment implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();
        DiaryCommentDao diaryCommentDao = DiaryCommentDao.getInstance();

        String method = request.getMethod();
        String strDiaryCode = null;
        String userId = null;
        String userNickName = null;
        String content = null;

        if(method.equals("POST")){
            strDiaryCode = request.getParameter("diaryCode");
            userId = request.getParameter("userId");
            userNickName = request.getParameter("userNickName");
            content = request.getParameter("content");

            boolean isVaild = true;

            if(strDiaryCode == null || strDiaryCode.isEmpty())
                isVaild = false;
            if (userId == null || userId.isEmpty())
                isVaild = false;
            if (userNickName == null || userNickName.isEmpty())
                isVaild = false;
            if(content == null || content.isEmpty())
                isVaild = false;

            if(isVaild){
                int diaryCode = Integer.parseInt(strDiaryCode);

                DiaryCommentRequestDto comment = new DiaryCommentRequestDto(diaryCode, userId, content, userNickName);

                if(comment != null){
                    boolean create = diaryCommentDao.createComment(comment);

                    if(create){
                        resObj.put("status", 200);
                        resObj.put("message", "댓글 생성 성공");
                    }

                }else{
                    resObj.put("status", 400);
                    resObj.put("message", "댓글 생성 실패");
                }
            }else{
                resObj.put("status", 400);
                resObj.put("message", "요청 값 누락");
            }
        }else{
            resObj.put("status", 404);
            resObj.put("message", "잘못된 요청");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().append(resObj.toString());

    }
}
