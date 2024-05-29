package diary.controller.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import diary.model.Diary;
import org.json.JSONObject;

import diary.model.DiaryDao;
import diary.model.DiaryRequestDto;
import diary.model.DiaryResponseDto;
import image.model.ImageDao;
import image.model.ImageRequestDto;
import util.Action;
import util.InputStreamParsor;

public class CreateDiaryAction implements Action {

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
        LocalDate today = LocalDate.now();
        Date sqlDate = Date.valueOf(today);

        boolean isVaild = true;
        boolean isGet = true;

        // 오늘날짜 다이어리를 이미 작성했는지 유효성 검사
        DiaryResponseDto diary = diaryDao.findDiaryOfDate(sqlDate);

        if (diary == null) {
            isVaild = true;
        } else {
            isVaild = false;
        }

        if (isVaild) {

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
                    } else if (name.equals("babycode")) {
                        babyCode = InputStreamParsor.parseToString(in).trim();
                        System.out.println(babyCode);
                    } else if (name.equals("title")) {
                        title = InputStreamParsor.parseToString(in).trim();
                        System.out.println(title);
                    } else if (name.equals("content")) {
                        content = InputStreamParsor.parseToString(in).trim();
                        System.out.println(content);
                    } else if (name.equals("category")) {
                        category = InputStreamParsor.parseToString(in).trim();
                        System.out.println(category);
                    }

                    in.close();
                }

                if (babyCode == null || babyCode.isEmpty()) {
                    isGet = false;
                    resObj.put("babycode", babyCode);
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
                    // 오늘의 다이어리 생성
                    DiaryRequestDto toDayDiary = new DiaryRequestDto(babyCode, sqlDate, title, content, category);
                    boolean isCorrect = diaryDao.createDiary(toDayDiary);

                    // 이미지가 있다면 , 이미지도 테이블에 저장
                    if (isCorrect) {

                        if (imageId != null && imageUrl != null) {
                            ImageDao imageDao = ImageDao.getInstance();

                            diary = diaryDao.findDiaryOfDate(sqlDate);
                            System.out.println(diary);
                            ImageRequestDto uploadImage = new ImageRequestDto(imageUrl, imageId, Diary.diary, String.valueOf(diary.getCode()));
                            System.out.println(uploadImage);

                            boolean isUploadSuccess = imageDao.createImage(uploadImage);

                            /*
                            if (isUploadSuccess) {
                                System.out.println("이미지 업로드 성공");
                            } else {
                                System.out.println("이미지 업로드 실패");
                            }
					        */

                            resObj.put("status", 200);
                            resObj.put("message_diary", "다이어리가 성공적으로 등록되었습니다.");
                            if (isUploadSuccess) {
                                resObj.put("message_image", "이미지가 성공적으로 등록되었습니다.");
                            } else {
                                resObj.put("error_image", "이미지 등록에 실패하였습니다.");
                            }
                        }

                    } else {
                        resObj.put("status", 400);
                        resObj.put("message_diary", "다이어리 등록 실패");
                    }

                } else {
                    resObj.put("status", 400);
                    resObj.put("message", "입력이 안된 항목이 있습니다.");
                }

            } else {
                resObj.put("status", 404);
                resObj.put("error", "요청방법을 확인해주세요.");
            }

        } else {
//			System.out.println("이미 오늘 다이어리를 작성완료했습니다. 일기를 수정해주세요.");
            resObj.put("status", 400);
            resObj.put("message_diary", "금일 다이어리 작성을 완료했습니다. 수정기능을 이용해주세요.");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().append(resObj.toString());
    }

}