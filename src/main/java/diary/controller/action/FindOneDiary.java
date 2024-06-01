package diary.controller.action;

import diary.model.Diary;
import diary.model.DiaryDao;
import diary.model.DiaryResponseDto;
import image.model.ImageDao;
import image.model.ImageResponseDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class FindOneDiary implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        DiaryDao diaryDao = DiaryDao.getInstance();

        String method = request.getMethod();

        String dateStr = null;
        String babycode = null;

        if (method.equals("GET")) {

            dateStr = request.getParameter("date");
            babycode = request.getParameter("babycode");

            boolean isVaild = true;

            if(dateStr == null && dateStr.isEmpty())
                isVaild = false;
            if(babycode == null && babycode.isEmpty())
                isVaild = false;


            if (isVaild) {
                Date date = Date.valueOf(dateStr);
                DiaryResponseDto diary = diaryDao.findDiaryOfDateAndCode(date,babycode);

                if (diary != null) {

                    ImageDao imageDao = ImageDao.getInstance();
                    ImageResponseDto image = imageDao.findImageByCodeAndType(String.valueOf(diary.getCode()), Diary.diary);

                    resObj.put("status", 200);
                    resObj.put("code", diary.getCode());
                    resObj.put("baby_code", diary.getBabyCode());
                    resObj.put("date", diary.getDate());
                    resObj.put("title", diary.getTitle());
                    resObj.put("content", diary.getContent());
                    resObj.put("category", diary.getCategory());
                    resObj.put("reg_date", diary.getRegDate());
                    resObj.put("mod_date", diary.getModDate());

                    // 해당일자의 이미지가 있고, 사용가능 상태일때
                    if (image != null && image.isStatus()) {
                        resObj.put("imgNum", image.getNum());
                        resObj.put("imgUrl", image.getUrl());
                        resObj.put("imgId", image.getId());
                        resObj.put("imgType", image.getType());
                    }

                } else {
                    resObj.put("status", 400);
                    resObj.put("message", "해당일의 다이어리 검색 실패");
                }

            } else {
                resObj.put("status", 400);
                resObj.put("message", "잘못된 요청");
            }

        } else {
            resObj.put("status", 404);
            resObj.put("message", "요청방법을 확인해주세요.");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().append(resObj.toString());

    }
}
