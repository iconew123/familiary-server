package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.Action;

public class DeleteAction implements Action  {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String method = request.getMethod();
      System.out.println("method : " + method);

      if (method.equals("DELETE")) {
         
         String password = request.getParameter("password");
         boolean isValid = true;

         if (password == null || password.equals(""))
            isValid = false;
         

         JSONObject resObj = new JSONObject();

         response.setCharacterEncoding("UTF-8");
         response.setContentType("application/json;charset=utf-8");
         
         if (isValid) {
            UserDao userDao = UserDao.getInstance();
            HttpSession session = request.getSession();
            UserResponseDto user = (UserResponseDto) session.getAttribute("user");
            String id = user.getId();
            
            
         }
//         UserDao userDao = UserDao.getInstance();
//         HttpSession session = request.getSession();
//         UserResponseDto user = (UserResponseDto) session.getAttribute("user");
//
//         String id = user.getId();
//
//         UserRequestDto userDto = new UserRequestDto();
//         userDto.setPassword(password);
//
//         boolean result = userDao.deleteUser(userDto);
      
      }
   }

}