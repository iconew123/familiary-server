package diary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import diary.model.DiaryDao;
import diary.model.DiaryRequestDto;
import util.Action;

public class CreateDiaryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		System.out.println("method : " + method);

		if (method.equals("POST")) {
			String babycode = request.getParameter("babycode");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String category = request.getParameter("category");

			DiaryDao diaryDao = DiaryDao.getInstance();
			DiaryRequestDto diary = new DiaryRequestDto(babycode, title, content, category);

			JSONObject resObj = new JSONObject();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf8");
			if (diary != null) {
				boolean isVaild = diaryDao.createDiary(diary);
				if (isVaild) {
					resObj.put("status", 200);
					resObj.put("message", "다이어리가 성공적으로 등록되었습니다.");
					resObj.put("babycode", babycode);
					resObj.put("title", title);
					resObj.put("content", content);
					resObj.put("category", category);

					response.getWriter().append(resObj.toString());
				} else {
					resObj.put("status", 404);
					resObj.put("message", "다이어리 등록 실패");

					response.getWriter().append(resObj.toString());
				}
			}
		} else {
			response.sendError(404, "유효하지 않은 접근 경로입니다.");
		}
	}
}
