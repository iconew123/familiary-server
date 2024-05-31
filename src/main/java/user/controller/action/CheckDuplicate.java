package user.controller.action;

import org.json.JSONObject;
import user.model.User;
import user.model.UserDao;
import user.model.UserRequestDto;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckDuplicate implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equals("POST")) {

            String field = request.getParameter("field");
            String value = request.getParameter("value");

            boolean isValid = true;

            if (field == null || field.equals(""))
                isValid = false;


            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            JSONObject resObj = new JSONObject();


            if (isValid) {
                UserDao userDao = UserDao.getInstance();
                UserRequestDto userDto = new UserRequestDto();

                boolean result = false;

                if (field.equals("id") ) {
                    User user = userDao.findUserById(value);
                    if (user!=null) {
                        result = true;
                    }
                }

                else if (field.equals(("nickname"))) {
                    User user = userDao.findUserByNickname(value);
                    if (user!=null) {
                        result = true;
                    }
                }
                else if (field.equals(("phone"))) {
                    User user = userDao.findUserByphone(value);
                    if (user!=null) {
                        result = true;
                    }
                }

                else if (field.equals(("email"))) {
                    User user = userDao.findUserByEamil(value);
                    if (user!=null) {
                        result = true;
                    }
                }

                if (result) {
                    resObj.put("status", 200);
                    resObj.put("message", "Duplicate checked successfully.");

                } else {
                    response.sendError(400);
                    resObj.put("status", 400);
                    resObj.put("message", "No Existed User");
                }

            } else {
                response.sendError(500);
                resObj.put("status", 500);
                resObj.put("message", "Database Error");
            }
            response.getWriter().append(resObj.toString());

        }


    }
}