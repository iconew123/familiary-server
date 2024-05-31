package baby.controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enroll.model.EnrollDao;
import image.model.ImageDao;
import image.model.ImageResponseDto;
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

        // CORS 설정
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        // 요청에서 아기 코드 가져오기
        String babyCode = request.getParameter("baby_code");
        String userId = request.getParameter("user_id");
        String type = "baby";
        // 아기 정보를 JSON 형식으로 변환하여 응답 보내기
        JSONObject jsonObj = new JSONObject();

        if (babyCode == null || babyCode.isEmpty()) {
            jsonObj.put("status", 400);
            jsonObj.put("message", "아기 코드가 제공되지 않았습니다.");
            response.getWriter().write(jsonObj.toString());
            return;
        }

        try {
            // 아기 정보를 데이터베이스에서 가져오기
            BabyDao babyDao = new BabyDao();
            Baby baby = babyDao.findBabyByCode(babyCode);

            if (baby == null) {
                jsonObj.put("status", 404);
                jsonObj.put("message", "아기 정보를 찾을 수 없습니다.");
                response.getWriter().write(jsonObj.toString());
                return;
            }

            ImageDao imageDao = ImageDao.getInstance();
            ImageResponseDto image = imageDao.findImageByCodeAndType(babyCode, type);

            EnrollDao enrollDao = EnrollDao.getInstance();
            String position = enrollDao.checkPosition(babyCode, userId);

            jsonObj.put("status", 200);
            jsonObj.put("message", "아기 정보를 성공적으로 가져왔습니다.");

            jsonObj.put("code", baby.getCode());
            jsonObj.put("nickname", baby.getNickname());
            jsonObj.put("name", baby.getName());
            jsonObj.put("gender", baby.getGender());
            jsonObj.put("expected_date", baby.getExpected_date());
            jsonObj.put("blood_type", baby.getBlood_type());
            jsonObj.put("position", position);
            jsonObj.put("url", image != null ? image.getUrl() : null);
        } catch (Exception e){
            jsonObj.put("status", 500);
            jsonObj.put("message", "서버에서 에러가 발생했습니다.");
            e.printStackTrace();
        }
        
        response.getWriter().write(jsonObj.toString());
    }


}