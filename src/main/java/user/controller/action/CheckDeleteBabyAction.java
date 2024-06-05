package user.controller.action;

import enroll.model.EnrollDao;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckDeleteBabyAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("user_id");

        JSONObject jsonObj = new JSONObject();

        if (userId == null || userId.isEmpty()) {
            jsonObj.put("status", 400);
            jsonObj.put("message", "유저 정보가 제공되지 않았습니다.");
            response.getWriter().write(jsonObj.toString());
            return;
        }

        EnrollDao dao = new EnrollDao();
        boolean isExist = dao.checkBaby(userId);

        jsonObj.put("isExist", isExist);

        response.getWriter().write(jsonObj.toString());
    }
}
