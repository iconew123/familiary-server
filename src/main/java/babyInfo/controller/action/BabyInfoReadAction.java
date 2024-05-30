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

        // CORS 설정
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        System.out.println("진입1");
        String babyCode = request.getParameter("code");
        String date = request.getParameter("date");


        System.out.println("진입4");
        System.out.println("date" + date);

        BabyInfoDao dao = new BabyInfoDao();
        BabyInfo info = dao.findBabyInfoByCodeAndDate(babyCode, date);

        JSONObject jsonObj = new JSONObject();
        System.out.println("진입5");
        jsonObj.put("baby_code", info.getBaby_code());
        jsonObj.put("date", info.getDate());
        jsonObj.put("height", info.getHeight());
        jsonObj.put("weight", info.getWeight());
        jsonObj.put("spec_note", info.getSpec_note());

        // 클라이언트로 JSON 응답을 보냄
        response.getWriter().write(jsonObj.toString());

    }
}
