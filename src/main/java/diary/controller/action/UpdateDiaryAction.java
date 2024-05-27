package diary.controller.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import diary.model.DiaryDao;
import diary.model.DiaryRequestDto;
import diary.model.DiaryResponseDto;
import image.model.ImageDao;
import image.model.ImageRequestDto;
import org.json.JSONObject;
import util.Action;
import util.InputStreamParsor;


public class UpdateDiaryAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiaryDao diaryDao = DiaryDao.getInstance();

        String method = request.getMethod();
        String babyCode = null;
        String title = null;
        String content = null;
        String category = null;
        String imageId = null;
        String imageUrl = null;
        Date date = Date.valueOf(request.getParameter("date"));
        System.out.println(date);

        if (method.equals("POST")) {

            List<Part> parts = (List<Part>) request.getParts();

            for (Part part : parts) {
                String name = part.getName();
                String type = part.getContentType();

                InputStream in = part.getInputStream();

                if (name.equals("photo")) {
                    try {
                        JSONObject jsonResponse = InputStreamParsor.uploadImage(in, type);
                        imageId = jsonResponse.getJSONObject("data").getString("id");
                        imageUrl = jsonResponse.getJSONObject("data").getString("url");
                        System.out.println("image ID: " + imageId);
                        System.out.println("Image URL: " + imageUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServletException("image업로드 실패", e);
                    }
                } else if (name.equals("date")) {
                    date = date.valueOf(InputStreamParsor.parseToString(in).trim());
                    System.out.println("date: " + date);
                } else if (name.equals("babycode")) {
                    babyCode = InputStreamParsor.parseToString(in).trim();
                    System.out.println("babycode: " + babyCode);
                } else if (name.equals("title")) {
                    title = InputStreamParsor.parseToString(in).trim();
                    System.out.println("title: " + title);
                } else if (name.equals("content")) {
                    content = InputStreamParsor.parseToString(in).trim();
                    System.out.println("content: " + content);
                } else if (name.equals("category")) {
                    category = InputStreamParsor.parseToString(in).trim();
                    System.out.println("category: " + category);
                }

                in.close();
            }

            DiaryResponseDto diary = diaryDao.findDiaryOfDate(date);

            if (diary != null) {
                DiaryRequestDto updateDiary = new DiaryRequestDto(diary.getCode(), babyCode, date, title, content, category);
                System.out.println(updateDiary);
                boolean isUpdate = diaryDao.updateDiary(updateDiary);
                if (isUpdate) {
                    System.out.println("다이어리 업데이트 완료");
                } else {
                    System.out.println("다이어리 업데이트 실패");
                }

                if (imageId != null && imageUrl != null) {
                    ImageDao imageDao = ImageDao.getInstance();

                    ImageRequestDto updateImage = new ImageRequestDto(imageUrl, imageId, "diary", String.valueOf(diary.getCode()));
                    boolean isValid = imageDao.updateImage(updateImage);
                    System.out.println(updateImage);
                    if (isValid) {
                        System.out.println("이미지 업데이트 완료");
                    } else {
                        System.out.println("이미지 업데이트 실패");
                    }

                } else {
                    System.out.println("바꿀 이미지가 없습니다.");
                }
            } else {
                System.out.println("등록된 다이어리가 없습니다.");
            }

        }


    }

}
