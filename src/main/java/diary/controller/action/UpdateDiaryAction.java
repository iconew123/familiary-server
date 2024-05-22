package diary.controller.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.controller.DiaryAction;
import diary.model.DiaryDao;
import diary.model.DiaryRequestDto;


public class UpdateDiaryAction implements DiaryAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		
		if(method.equals("POST")) {
			String strDate = request.getParameter("date");
			Date date = Date.valueOf(strDate);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// 확인용
			System.out.println("strDate" + strDate);
			System.out.println("date" + date);
			
			DiaryDao diaryDao = DiaryDao.getInstance();
			
		}
		
		
	}

}
