package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import user.model.UserDao;
import user.model.UserResponseDto;
import util.Action;

public class LoginAction implements Action {
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String method = request.getMethod();

      if (method.equals("POST")) {
         String id = request.getParameter("id");
         String password = request.getParameter("password");

         boolean isValid = true;

         if (id == null || id.equals(""))
            isValid = false;
         else if (password == null || password.equals(""))
            isValid = false;
         
         JSONObject resObj = new JSONObject();

         response.setCharacterEncoding("UTF-8");
         response.setContentType("application/json;charset=utf-8");
         
         if (isValid) {

            UserDao userDao = UserDao.getInstance();
            UserResponseDto user = userDao.findUserById(id);


            if (user != null) {

               resObj.put("status", 200);
               resObj.put("message", "User login successfully.");
               resObj.put("id", id);
               resObj.put("password", password);

            } else {
               resObj.put("status", 400);
               resObj.put("message", "No Existed User");

            }

         }else {
            resObj.put("status", 500);
            resObj.put("message", "Database Error");
         }

         response.getWriter().append(resObj.toString());

      }
   }
}