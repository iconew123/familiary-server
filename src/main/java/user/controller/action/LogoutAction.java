package user.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import util.Action;

public class LogoutAction implements Action {
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String method = request.getMethod();

      JSONObject resObj = new JSONObject();

      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json;charset=utf-8");
      
      if (method.equals("POST")) {
         resObj.put("status", 200);
         resObj.put("message", "Logout successfully.");
         HttpSession session = request.getSession();
         session.removeAttribute("user");
      }
   }
}