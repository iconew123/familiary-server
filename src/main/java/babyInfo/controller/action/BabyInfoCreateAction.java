package babyInfo.controller.action;

import babyInfo.model.BabyInfo;
import babyInfo.model.BabyInfoDao;
import babyInfo.model.BabyInfoRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BabyInfoCreateAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String baby_code = request.getParameter("code");
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        String spec_note = request.getParameter("spec_note");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(formatter);

        boolean isValid = true;

        BabyInfoRequestDto babyInfo = new BabyInfoRequestDto(baby_code, Integer.parseInt(height), Integer.parseInt(weight), spec_note);
        BabyInfoDao dao = new BabyInfoDao();
        BabyInfo baby = dao.findBabyInfoByCodeAndDate(baby_code, date);
        if(baby==null){
            isValid = true;
        } else {
            isValid = false;
        }

        JSONObject resObj = new JSONObject();

        if(isValid){
            dao.createBabyInfo(babyInfo);

            resObj.put("status", 200);
            resObj.put("message", "아기 정보가 성공적으로 등록되었습니다.");
        } else {
            resObj.put("status", 400);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf8");

        response.getWriter().append(resObj.toString());
    }
}
