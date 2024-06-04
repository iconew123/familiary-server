package user.controller.action;

import org.json.JSONObject;
import user.model.UserDao;
import user.model.UserResponseDto;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyPassword implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        JSONObject resObj = new JSONObject();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        if (id != null && !id.isEmpty() && password != null && !password.isEmpty()) {
            UserDao userDao = UserDao.getInstance();
            UserResponseDto user = userDao.findUserByIdAndPassword(id,password);
            boolean isVerity = false;

            if (user !=null) {
                isVerity = true;
                resObj.put("status", 200);
                resObj.put("isVerity", isVerity);
            } else {
                resObj.put("status", 400);
                resObj.put("message", "Invalid password.");
                resObj.put("isVerity", isVerity);
            }
        } else {
            resObj.put("isValid", false);
            resObj.put("status", 400);
            resObj.put("message", "Invalid request.");
        }
        response.getWriter().append(resObj.toString());
    }
}