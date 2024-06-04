package babyInfo.controller.action;

import babyInfo.model.BabyInfo;
import babyInfo.model.BabyInfoDao;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class BabyInfoReadAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        try {
            String babyCode = request.getParameter("code");
            String date = request.getParameter("date");

            if (babyCode == null || date == null) {
                throw new IllegalArgumentException("code 또는 date 파라미터가 누락되었습니다.");
            }

            System.out.println("진입4");

            BabyInfoDao dao = new BabyInfoDao();
            BabyInfo info = dao.findBabyInfoByCodeAndDate(babyCode, date);

            if (info == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"해당하는 정보를 찾을 수 없습니다.\"}");
                return;
            }

            JSONObject jsonObj = new JSONObject();
            jsonObj.put("baby_code", info.getBaby_code());
            jsonObj.put("date", info.getDate());
            jsonObj.put("height", info.getHeight());
            jsonObj.put("weight", info.getWeight());
            jsonObj.put("spec_note", info.getSpec_note());

            // 클라이언트로 JSON 응답을 보냄
            response.getWriter().append(jsonObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"서버 오류가 발생했습니다.\"}");
        }
    }
}