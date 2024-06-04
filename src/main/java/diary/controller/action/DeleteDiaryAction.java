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


public class DeleteDiaryAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject resObj = new JSONObject();

        DiaryDao diaryDao = DiaryDao.getInstance();

        String method = request.getMethod();
        String babyCode = null;
        String dateStr = null;

        boolean isGet = true;
        boolean checkImg = false;
        boolean checkDiary = false;

        if (method.equals("DELETE")) {
            babyCode = request.getParameter("babycode");
            dateStr = request.getParameter("date");

            if (babyCode == null || babyCode.isEmpty()) {
                isGet = false;
                resObj.put("babyCode", babyCode);
            }
            if (dateStr == null || dateStr.isEmpty()) {
                isGet = false;
                resObj.put("date", dateStr);
            }

            Date date = Date.valueOf(dateStr);

            // 제대로 된 값이 넘어왔을 때,
            if (isGet) {
                DiaryResponseDto diary = diaryDao.findDiaryOfDateAndCode(date,babyCode);

                // 해당 날짜에 다이어리가 작성이 되었으면
                if (diary != null) {
                    ImageDao imageDao = ImageDao.getInstance();

                    ImageResponseDto image = imageDao.findImageByCodeAndType(String.valueOf(diary.getCode()), Diary.diary);
                    if (image != null) {
                        checkImg = imageDao.deleteImage(image.getNum());
                    }

                    checkDiary = diaryDao.deleteDiary(diary.getDate(), diary.getBabyCode());
                    if (checkDiary && checkImg) {
                        resObj.put("status", 200);
                        resObj.put("message_diary", "다이어리 삭제 완료");
                        resObj.put("message_image", "이미지 삭제 완료");
                    } else {
                        resObj.put("status", 400);
                        if (!checkDiary)
                            resObj.put("message_diary", "다이어리 삭제 실패");
                        if (!checkImg)
                            resObj.put("message_image", "이미지 삭제 실패");
                    }

                } else {
//                    System.out.println("해당 일자에 다이어리 기록이 없습니다.");
                    resObj.put("status", 400);
                    resObj.put("message_diary", "해당 일자의 다이어리 기록을 찾을 수 없습니다.");
                }

            } else {
                resObj.put("status", 400);
                resObj.put("message", "입력이 안된 항목이 있습니다.");
            }

        } else {
//                System.out.println("잘못된 요청");
            resObj.put("status", 404);
            resObj.put("error", "요청방법을 확인해주세요.");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().append(resObj.toString());
    }
}
