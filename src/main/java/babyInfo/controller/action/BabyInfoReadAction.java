package babyInfo.controller.action;

import babyInfo.model.BabyInfo;
import babyInfo.model.BabyInfoDao;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BabyInfoReadAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        try {
            String babyCode = request.getParameter("code");
            String date = request.getParameter("date");


            BabyInfoDao dao = new BabyInfoDao();
            BabyInfo info = dao.findBabyInfoByCodeAndDate(babyCode, date);

            JSONObject jsonObj = new JSONObject();
            if (info != null) {
                jsonObj.put("baby_code", info.getBaby_code());
                jsonObj.put("date", info.getDate());
                jsonObj.put("height", info.getHeight());
                jsonObj.put("weight", info.getWeight());
                jsonObj.put("spec_note", info.getSpec_note());
            } else {
                jsonObj.put("status", 400);
                jsonObj.put("message", "해당일의 정보 검색 실패");
            }

            response.getWriter().append(jsonObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"서버 오류가 발생했습니다.\"}");
        }
    }
}