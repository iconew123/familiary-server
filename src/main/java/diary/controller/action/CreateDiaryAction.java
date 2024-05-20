package diary.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diary.controller.DiaryAction;

public class CreateDiaryAction implements DiaryAction{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("다이어리 생성 로직");
		
	}

}
