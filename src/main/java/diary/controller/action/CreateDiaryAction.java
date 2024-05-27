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

		// 오늘날짜 다이어리를 이미 작성했는지 유효성 검사
		DiaryResponseDto diary = diaryDao.findDiaryOfDate(sqlDate);
		boolean isVaild = true;

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

				// 오늘의 다이어리 생성
				DiaryRequestDto toDayDiary = new DiaryRequestDto(babyCode, sqlDate, title, content, category);
				boolean isCorrect = diaryDao.createDiary(toDayDiary);

				// 이미지가 있다면 , 이미지도 테이블에 저장
				if (isCorrect && (imageId != null && imageUrl != null)) {
					ImageDao imageDao = ImageDao.getInstance();

					diary = diaryDao.findDiaryOfDate(sqlDate);
					System.out.println(diary);
					ImageRequestDto uploadImage = new ImageRequestDto(imageUrl, imageId, "diary", diary.getCode());
					System.out.println(uploadImage);

					boolean isUploadSuccess = imageDao.createImage(uploadImage);

					if (isUploadSuccess) {
						System.out.println("이미지 업로드 성공");
					} else {
						System.out.println("이미지 업로드 실패");
					}
				}

			}
		} else {
			System.out.println("이미 오늘 다이어리를 작성완료했습니다. 일기를 수정해주세요.");
		}

	}

}