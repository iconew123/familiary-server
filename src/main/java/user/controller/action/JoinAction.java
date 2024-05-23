package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import util.Action;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;

public class JoinAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String method = request.getMethod();


      if (method.equals("POST")) {
         String id = request.getParameter("id");
         String password = request.getParameter("password");
         String nickname = request.getParameter("nickname");
         String name = request.getParameter("name");
         String securityNumber = request.getParameter("security_number");
         String telecom = request.getParameter("telecom");
         String phone = request.getParameter("phone");
         String email = request.getParameter("email");
         String address = request.getParameter("address");

         boolean isValid = true;

         if (id == null || id.equals(""))
            isValid = false;
         else if (password == null || password.equals(""))
            isValid = false;
         else if (nickname == null || nickname.equals(""))
            isValid = false;
         else if (name == null || name.equals(""))
            isValid = false;
         else if (securityNumber == null || securityNumber.equals(""))
            isValid = false;
         else if (telecom == null || telecom.equals(""))
            isValid = false;
         else if (phone == null || phone.equals(""))
            isValid = false;

         JSONObject resObj = new JSONObject();

         response.setCharacterEncoding("UTF-8");
         response.setContentType("application/json;charset=utf-8");

         if (isValid) {
            UserRequestDto userDto = new UserRequestDto(id, password, nickname, name, securityNumber, telecom,
                  phone, email, address);
            UserDao userDao = UserDao.getInstance();
            UserResponseDto user = userDao.createUser(userDto);

            if (user != null) {
               resObj.put("status", 200);
               resObj.put("message", "User created successfully.");
               resObj.put("id", id);
               resObj.put("nickname", nickname);
               resObj.put("security_number", securityNumber);
               resObj.put("telecom", telecom);
               resObj.put("phone", phone);
               resObj.put("email", email);
               resObj.put("address", address);

            } else {
               resObj.put("status", 400);
               resObj.put("message", "No Existed User");
            }
         } else {
            resObj.put("status", 500);
            resObj.put("message", "Database Error");
         }

         response.getWriter().append(resObj.toString());
      }
   }
}