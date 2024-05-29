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
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
      response.setHeader("Access-Control-Max-Age", "3600");
      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
      String method = request.getMethod();
      System.out.println("하이");
      if (method.equals("POST")) {
         String id = request.getParameter("id");
         System.out.println("id"+id);
         String password = request.getParameter("password");
         String nickname = request.getParameter("nickname");
         String name = request.getParameter("name");
         String securityNumber = request.getParameter("securityNumber");
         String telecom = request.getParameter("telecom");
         String phone = request.getParameter("phone");
         String email = request.getParameter("email");
         String address = request.getParameter("address");

         boolean isValid = true;
         System.out.println(id);
         System.out.println(password);
         System.out.println(nickname);
         System.out.println(name);
         System.out.println(securityNumber);
         System.out.println(telecom);
         System.out.println(phone);
         System.out.println(email);
         System.out.println(address);

         if (id == null || id.equals("")) {
            isValid = false;
         }
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
         System.out.println(isValid);
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
               resObj.put("securityNumber", securityNumber);
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