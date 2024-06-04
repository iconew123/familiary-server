package diaryComment.controller.action;

import diaryComment.model.DiaryCommentDao;
import diaryComment.model.DiaryCommentResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchAllComment implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray resArr = new JSONArray();
        JSONObject errorObj = new JSONObject();

        DiaryCommentDao diaryCommentDao = DiaryCommentDao.getInstance();

        String method = request.getMethod();
        String diaryCode = null;

        if(method.equals("GET")) {
            diaryCode = request.getParameter("diaryCode");

            if(diaryCode != null && !diaryCode.isEmpty()) {

                List<DiaryCommentResponseDto> list = new ArrayList<>();
                list = diaryCommentDao.findAllComment(Integer.parseInt(diaryCode));

                if(list.size() > 0) {
                    for(DiaryCommentResponseDto dcList : list) {
                        JSONObject resObj = new JSONObject();
                        resObj.put("code", dcList.getCode());
                        resObj.put("diaryCode", dcList.getDiaryCode());
                        resObj.put("userId", dcList.getUserId());
                        resObj.put("userNickName", dcList.getNickName());
                        resObj.put("content", dcList.getContent());
                        resObj.put("regDate", dcList.getRegDate());
                        resObj.put("modDate", dcList.getModDate());
                        resArr.put(resObj);
                    }
                }else{
                    errorObj.put("status", 400);
                    errorObj.put("message", "댓글이 없습니다.");
                }
            }else{
                errorObj.put("status", 400);
                errorObj.put("message", "잘못된 요청");
            }
        }else{
            errorObj.put("status", 404);
            errorObj.put("message", "잘못된 요청");
        }

        if (errorObj.length() > 0) {
            resArr.put(errorObj);
        }

        String jsonString = resArr.toString();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }
}
