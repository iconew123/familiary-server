package community.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.controller.CommunityAction;
import community.model.CommunityDao;

public class CreateCommunityAction implements CommunityAction {
	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CommunityDao communityDao = CommunityDao.getInstance();
		
		String userNickname = request.getParameter("userNickname");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String photo = request.getParameter("photo");
		
	}
	

}
