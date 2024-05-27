package diary.controller.action;

import diary.model.DiaryDao;
import diary.model.DiaryResponseDto;
import image.model.ImageDao;
import image.model.ImageResponseDto;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;


public class DeleteDiaryAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiaryDao diaryDao = DiaryDao.getInstance();

        String method = request.getMethod();
        String babyCode = null;
        Date date = null;
        String type = "diary";


        if (method.equals("GET")) {
            babyCode = request.getParameter("babycode");
            date = Date.valueOf(request.getParameter("date"));

            // 제대로 된 파라미터값이 들어왔을때
            if (babyCode != null && date != null) {
                DiaryResponseDto diary = diaryDao.findDiaryOfDate(date);

                // 해당 날짜에 다이어리가 작성이 되었으면
                if (diary != null) {
                    ImageDao imageDao = ImageDao.getInstance();

                    ImageResponseDto image = imageDao.findImageByDateAndBabyCode(date,babyCode);
                    if (image != null) {

                    }

                } else {
                    System.out.println("해당 일자에 다이어리 기록이 없습니다.");
                }
            } else {
                System.out.println("잘못된 요청");
            }

        }
    }
}
