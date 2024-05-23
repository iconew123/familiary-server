package community.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.CommunityDao;
import util.Action;

public class CreateCommunityAction implements Action {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CommunityDao communityDao = CommunityDao.getInstance();
		
		String userNickname = request.getParameter("userNickname");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String photo = request.getParameter("photo");
		
	}
	

}
