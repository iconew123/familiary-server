package enroll.controller.action;

import enroll.model.EnrollDao;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReadBabyCodeAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // CORS 설정
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        // 요청에서 userId 받아오기
        String userId = request.getParameter("user_id");
        System.out.println("user_id: " + userId );
        JSONObject jsonObj = new JSONObject();

        if (userId == null || userId.isEmpty()) {
            jsonObj.put("status", 400);
            jsonObj.put("message", "유저 정보가 제공되지 않았습니다.");
            response.getWriter().write(jsonObj.toString());
            return;
        }

        EnrollDao dao = new EnrollDao();
        List<String> babyNickname = dao.findBabyCodeByNickName(userId);
        List<String> babyCodes = dao.findBabyCodeByCode(userId);

        JSONArray babyNicknames = null;
        if (babyNickname == null || babyNickname.isEmpty()) {
            jsonObj.put("status", 404);
            jsonObj.put("message", "해당 유저에 대한 베이비 코드가 없습니다.");
        } else {
            babyNicknames = new JSONArray(babyNickname);
            jsonObj.put("status", 200);
            jsonObj.put("nicknames", babyNicknames);
            jsonObj.put("codes", babyCodes);
        }

        System.out.println("value: " + babyNicknames);
        response.getWriter().write(jsonObj.toString());


    }
}
