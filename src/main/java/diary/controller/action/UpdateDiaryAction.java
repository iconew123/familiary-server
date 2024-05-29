package diary.controller.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import diary.model.Diary;
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
        JSONObject resObj = new JSONObject();

        DiaryDao diaryDao = DiaryDao.getInstance();

        String method = request.getMethod();
        String babyCode = null;
        String title = null;
        String content = null;
        String category = null;
        String imageId = null;
        String imageUrl = null;
        String dateStr = null;

        boolean isGet = true;
        boolean isUpdate = false;
        boolean isValid = false;

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
                    dateStr = InputStreamParsor.parseToString(in).trim();
//                    System.out.println("date: " + date);
                } else if (name.equals("babycode")) {
                    babyCode = InputStreamParsor.parseToString(in).trim();
//                    System.out.println("babycode: " + babyCode);
                } else if (name.equals("title")) {
                    title = InputStreamParsor.parseToString(in).trim();
//                    System.out.println("title: " + title);
                } else if (name.equals("content")) {
                    content = InputStreamParsor.parseToString(in).trim();
//                    System.out.println("content: " + content);
                } else if (name.equals("category")) {
                    category = InputStreamParsor.parseToString(in).trim();
//                    System.out.println("category: " + category);
                }

                in.close();
            }

            if (dateStr == null || dateStr.isEmpty()) {
                isGet = false;
                resObj.put("dateStr", dateStr);
            }
            if (babyCode == null || babyCode.isEmpty()) {
                isGet = false;
                resObj.put("babyCode", babyCode);
            }
            if (title == null || title.isEmpty()) {
                isGet = false;
                resObj.put("title", title);
            }
            if (content == null || content.isEmpty()) {
                isGet = false;
                resObj.put("content", content);
            }
            if (category == null || category.isEmpty()) {
                isGet = false;
                resObj.put("category", category);
            }

            if (isGet) {
                Date date = Date.valueOf(dateStr);
                DiaryResponseDto diary = diaryDao.findDiaryOfDate(date);

                if (diary != null) {
                    DiaryRequestDto updateDiary = new DiaryRequestDto(diary.getCode(), babyCode, date, title, content, category);
                    isUpdate = diaryDao.updateDiary(updateDiary);

                /*
                if (isUpdate) {
                    System.out.println("다이어리 업데이트 완료");
                } else {
                    System.out.println("다이어리 업데이트 실패");
                }
                */

                    if (isUpdate) {
                        resObj.put("status", 200);
                        resObj.put("message_diary", "다이어리 업데이트 성공");
                    } else {
                        resObj.put("status", 400);
                        resObj.put("message_diary", "다이어리 업데이트 실패");
                    }

                    if (imageId != null && imageUrl != null) {
                        ImageDao imageDao = ImageDao.getInstance();

                        ImageRequestDto updateImage = new ImageRequestDto(imageUrl, imageId, Diary.diary, String.valueOf(diary.getCode()));
                        isValid = imageDao.updateImage(updateImage);

                        /*
                        if (isValid) {
                            System.out.println("이미지 업데이트 완료");
                        } else {
                            System.out.println("이미지 업데이트 실패");
                        }
                        */

                        if (isValid)
                            resObj.put("message_image", "이미지 업데이트 성공");
                        else
                            resObj.put("message_image", "이미지 업데이트 실패");
                    } else {
//                    System.out.println("바꿀 이미지가 없습니다.");
                        resObj.put("message_image", "업데이트 할 이미지가 없습니다. 현재 이미지로 유지합니다.");
                    }
                } else {
//                System.out.println("등록된 다이어리가 없습니다.");
                    resObj.put("status", 400);
                    resObj.put("message_diary", "해당 일자의 다이어리 기록을 찾을 수 없습니다.");
                }

            } else {
                resObj.put("status", 400);
                resObj.put("message", "입력이 안된 항목이 있습니다.");
            }

        } else {
            resObj.put("status", 404);
            resObj.put("error", "요청방법을 확인해주세요.");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().append(resObj.toString());
    }

}
