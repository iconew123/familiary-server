package babyInfo.controller.action;

import baby.model.Baby;
import baby.model.BabyDao;
import babyInfo.model.BabyInfo;
import babyInfo.model.BabyInfoDao;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BabyInfoDeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String baby_code = request.getParameter("baby_code");
        String date = request.getParameter("date");

        BabyInfoDao dao = new BabyInfoDao();

        JSONObject jsonResponse = new JSONObject();
        boolean success = dao.deleteBabyInfo(baby_code, date);

        if(success) {
            jsonResponse.put("status", 200);
            jsonResponse.put("message", "정보가 성공적으로 삭제되었습니다.");
        } else {
            jsonResponse.put("status", 500);
            jsonResponse.put("message", "정보 삭제에 실패했습니다.");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(jsonResponse.toString());
    }
}
