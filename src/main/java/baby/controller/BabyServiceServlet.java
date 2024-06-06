package baby.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

@MultipartConfig
public class BabyServiceServlet extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String command = request.getParameter("command");
		String code = request.getParameter("code");

		if(command != null) {
			BabyActionFactory af = BabyActionFactory.getInstance();
			Action action = af.getAction(command);
			
			// 요청된 액션이 있는지 확인하여 액션 존재 시 해당 액션 실행, 없으면 404 오류 반환
			if(action != null) {
				action.execute(request, response);
			} else {
				response.sendError(404);
			}			
		} else {
			response.sendError(404);			
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
