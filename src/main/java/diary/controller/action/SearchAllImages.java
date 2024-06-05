package diary.controller.action;

import diary.model.Diary;
import diary.model.DiaryDao;
import diary.model.DiaryResponseDto;
import image.model.ImageDao;
import image.model.ImageResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SearchAllImages implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray resArr = new JSONArray();
        JSONObject errorObj = new JSONObject();

        ImageDao imageDao = ImageDao.getInstance();
        DiaryDao diaryDao = DiaryDao.getInstance();

        String method = request.getMethod();

        if (method.equals("GET")) {

            String type = Diary.diary;
            String babycode = request.getParameter("babycode");

            if (babycode != null && !babycode.isEmpty()) {
                List<ImageResponseDto> list = new ArrayList<>();
                list = imageDao.findAllImage(babycode, type);

                if (list.size() > 0) {
                    for (ImageResponseDto image : list) {
                        JSONObject resObj = new JSONObject();
                        resObj.put("imgurl", image.getUrl());
                        resObj.put("status", image.isStatus());

                        Date date = diaryDao.getDate(Integer.valueOf(image.getCode()));
                        resObj.put("Date", date);

                        resArr.put(resObj);
                    }
                } else {
                    errorObj.put("status", 400);
                    errorObj.put("message", "데이터를 찾을 수 없습니다.");
                }

            } else {
                errorObj.put("status", 400);
                errorObj.put("message", "babycode를 찾을 수 없거나 잘못된 값입니다.");
            }

        } else {
            errorObj.put("status", 404);
            errorObj.put("message", "잘못된 요청.");
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
