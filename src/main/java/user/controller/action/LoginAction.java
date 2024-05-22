package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserAction;
import user.model.UserDao;
import user.model.UserResponseDto;

public class LoginAction implements UserAction{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		
//		boolean isValid = true;
//		
//		if(id == null || id.equals(""))
//			isValid = false;
//		else if(password == null || password.equals(""))
//			isValid = false;
//		
//		if(isValid) {
//
//			UserDao userDao = UserDao.getInstance();
//			UserResponseDto user = userDao.findUserByIdAndPassword(id);
//			
//			if(user != null) {
//				response.sendRedirect("/main");				
//			} else {
//				response.sendRedirect("/login");				
//			}
//		} else {
//			response.sendRedirect("/login");
//		}
		

	}
}
