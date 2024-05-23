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
        String babyCode = request.getParameter("baby_code");

        // 아기 정보를 데이터베이스에서 가져오기
        BabyDao babyDao = new BabyDao();
        Baby baby = babyDao.findBabyByCode(babyCode);

        // 아기 정보를 JSON 형식으로 변환하여 응답 보내기
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("status", 200);
        jsonObj.put("message", "아기 정보를 성공적으로 가져왔습니다.");
        
        jsonObj.put("baby_code", baby.getCode());
        jsonObj.put("nickname", baby.getNickname());
        jsonObj.put("name", baby.getName());
        jsonObj.put("gender", baby.getGender());
        jsonObj.put("expected_date", baby.getExpected_date());
        jsonObj.put("bloodType", baby.getBlood_type());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        // jsonObj 객체를 문자열 형태로 변환한 후에, HttpServletResponse의 PrintWriter를 사용하여 클라이언트에게 전송하는 역할
        response.getWriter().append(jsonObj.toString());
    }
}