package baby.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import baby.model.Baby;
import baby.model.BabyDao;
import util.Action;

public class BabyInfoAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청에서 아기 코드 가져오기
        String babyCode = request.getParameter("babyCode");

        // 아기 코드가 없는 경우 에러 응답 보내기
        if (babyCode == null || babyCode.isEmpty()) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("status", 400);
            errorResponse.put("message", "아기 코드를 제공해야 합니다.");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().append(errorResponse.toString());
            return;
        }

        // 아기 정보를 데이터베이스에서 가져오기
        BabyDao babyDao = new BabyDao();
        Baby baby = babyDao.findBabyByCode(babyCode);

        // 아기가 존재하지 않는 경우 에러 응답 보내기
        if (baby == null) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("status", 404);
            errorResponse.put("message", "해당하는 아기를 찾을 수 없습니다.");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().append(errorResponse.toString());
            return;
        }

        // 아기 정보를 JSON 형식으로 변환하여 응답 보내기
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", 200);
        jsonResponse.put("message", "아기 정보를 성공적으로 가져왔습니다.");
        
        jsonResponse.put("babyCode", baby.getCode());
        jsonResponse.put("nickname", baby.getNickname());
        jsonResponse.put("name", baby.getName());
        jsonResponse.put("gender", baby.getGender());
        jsonResponse.put("expectedDate", baby.getExpected_date());
        jsonResponse.put("bloodType", baby.getBlood_type());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(jsonResponse.toString());
    }
}