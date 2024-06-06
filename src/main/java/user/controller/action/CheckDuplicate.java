package user.controller.action;

import org.json.JSONObject;
import user.model.UserDao;
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
            if (value == null || value.equals(""))
                isValid = false;

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            JSONObject resObj = new JSONObject();


            if (isValid) {
                UserDao userDao = UserDao.getInstance();
                boolean isDuplicate = userDao.isDuplicate(field, value);

                resObj.put("status", 200);
                resObj.put("message", "중복 체크가 성공적으로 완료되었습니다.");
                resObj.put("isDuplicate", isDuplicate);
            } else {
                resObj.put("status", 400);
                resObj.put("message", "잘못된 요청입니다.");
            }

            response.getWriter().append(resObj.toString());

        }
    }

}