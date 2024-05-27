package baby.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import baby.model.Baby;
import baby.model.BabyDao;
import util.Action;

public class BabyInfoAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // CORS 헤더 추가
        
    	// 요청에서 아기 코드 가져오기
        String babyCode = request.getParameter("code");

        // 아기 정보를 데이터베이스에서 가져오기
        BabyDao babyDao = new BabyDao();
        Baby baby = babyDao.findBabyByCode(babyCode);

        // 아기 정보를 JSON 형식으로 변환하여 응답 보내기
        JSONObject jsonObj = new JSONObject();
        
        jsonObj.put("status", 200);
        jsonObj.put("message", "아기 정보를 성공적으로 가져왔습니다.");
        
        jsonObj.put("code", baby.getCode());
        jsonObj.put("nickname", baby.getNickname());
        jsonObj.put("name", baby.getName());
        jsonObj.put("gender", baby.getGender());
        jsonObj.put("expected_date", baby.getExpected_date());
        jsonObj.put("bloodType", baby.getBlood_type());

        
        response.getWriter().write(jsonObj.toString());
    }
}