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

public class UpdateAction implements Action {
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String method = request.getMethod();
      System.out.println("method : " + method);

      if (method.equals("POST")) {

         String password = request.getParameter("password");
         String newPassword = request.getParameter("new-password");
         String newNickname = request.getParameter("nickname");
         String newTelecom = request.getParameter("telecom");
         String newPhone = request.getParameter("phone");
         String newEmail = request.getParameter("email");
         String newAdress = request.getParameter("adress");

         boolean isValid = true;

         if (password == null || password.equals(""))
            isValid = false;
         else if (newPassword == null || newPassword.equals(""))
            isValid = false;



         JSONObject resObj = new JSONObject();

         response.setCharacterEncoding("UTF-8");
         response.setContentType("application/json;charset=utf-8");

         if (isValid) {
            UserDao userDao = UserDao.getInstance();
            UserRequestDto userDto = new UserRequestDto();
            
            HttpSession session = request.getSession();
            UserResponseDto user = (UserResponseDto) session.getAttribute("user");
   

               if (!newEmail.equals(userDto.getEmail())) {
                  userDao.updateUserEmail(userDto, newEmail);
               

               if (!newPhone.equals(userDto.getPhone()) || !newTelecom.equals(userDto.getTelecom())) {
                  userDao.updateUserPhone(userDto, newTelecom, newPhone);
                  resObj.put("telecom", newTelecom);
                  resObj.put("phone", newPhone);
               }

            } else {

            }

         }

      }

   }

}