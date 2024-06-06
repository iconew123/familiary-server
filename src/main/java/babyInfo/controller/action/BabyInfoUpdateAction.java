package babyInfo.controller.action;

import babyInfo.model.BabyInfoDao;
import babyInfo.model.BabyInfoRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BabyInfoUpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String baby_code = request.getParameter("baby_code");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        String spec_note = request.getParameter("spec_note");
        String date = request.getParameter("date");

        BabyInfoRequestDto babyInfo = new BabyInfoRequestDto(baby_code, date, Integer.parseInt(height), Integer.parseInt(weight), spec_note);
        BabyInfoDao dao = new BabyInfoDao();
        dao.updateBabyInfo(babyInfo);

        // 결과를 응답하기
        JSONObject resObj = new JSONObject();
        resObj.put("status", 200);
        resObj.put("message", "아기 정보가 성공적으로 수정되었습니다.");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf8");

        response.getWriter().append(resObj.toString());
    }
}
