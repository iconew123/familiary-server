package babyInfo.controller.action;

import babyInfo.model.BabyInfoDao;
import babyInfo.model.BabyInfoRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;

public class BabyInfoCreateAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        String baby_code = request.getParameter("code");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        String spec_note = request.getParameter("spec_note");

        BabyInfoRequestDto babyInfo = new BabyInfoRequestDto(baby_code, Integer.parseInt(height), Integer.parseInt(weight), spec_note);
        BabyInfoDao dao = new BabyInfoDao();
        dao.createBabyInfo(babyInfo);

        // 결과를 응답하기
        JSONObject resObj = new JSONObject();
        resObj.put("status", 200);
        resObj.put("message", "아기 정보가 성공적으로 등록되었습니다.");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf8");

        response.getWriter().append(resObj.toString());
    }
}
