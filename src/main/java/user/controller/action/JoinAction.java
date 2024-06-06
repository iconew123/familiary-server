package user.controller.action;

import org.json.JSONObject;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinAction implements Action {

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String method = request.getMethod();

      if (method.equals("POST")) {
         String id = request.getParameter("id");
         String password = request.getParameter("password");
         String nickname = request.getParameter("nickname");
         String name = request.getParameter("name");
         String securityNumber = request.getParameter("securityNumber");
         String telecom = request.getParameter("telecom");
         String phone = request.getParameter("phone");
         String address = request.getParameter("address");
         String email = request.getParameter("email");


         boolean isValid = true;

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

         if (isValid) {
            UserRequestDto userDto = new UserRequestDto(id, password, nickname, name, securityNumber, telecom,
                    phone, address, email);
            UserDao userDao = UserDao.getInstance();
            UserResponseDto user = userDao.createUser(userDto);

            if (user != null) {
               resObj.put("status", 200);
               resObj.put("message", "User created successfully.");
            } else {
               response.sendError(400);
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