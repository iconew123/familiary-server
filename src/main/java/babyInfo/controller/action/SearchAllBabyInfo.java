package babyInfo.controller.action;

import babyInfo.model.BabyInfo;
import babyInfo.model.BabyInfoDao;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchAllBabyInfo implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONArray resArr = new JSONArray();
        JSONObject errorObj = new JSONObject();

        BabyInfoDao dao = BabyInfoDao.getInstance();

        String method = request.getMethod();

        if (method.equals("GET")) {
            String babyCode = request.getParameter("code");

            if (babyCode != null && !babyCode.isEmpty()) {
                List<BabyInfo> list = dao.findAllInfo(babyCode);

                if (!list.isEmpty()) {
                    for (BabyInfo info : list) {
                        JSONObject babyObj = new JSONObject();
                        babyObj.put("code", info.getBaby_code());
                        babyObj.put("date", info.getDate());
                        babyObj.put("height", info.getHeight());
                        babyObj.put("weight", info.getWeight());
                        babyObj.put("spec_note", info.getSpec_note());
                        babyObj.put("regDate", info.getReg_date());
                        babyObj.put("modDate", info.getMode_date());

                        resArr.put(babyObj);
                    }
                } else {
                    errorObj.put("status", 404);
                    errorObj.put("message", "데이터를 찾을 수 없습니다.");
                }
            } else {
                errorObj.put("status", 400);
                errorObj.put("message", "잘못된 babycode 값입니다.");
            }
        } else {
            errorObj.put("status", 405);
            errorObj.put("message", "허용되지 않은 메서드입니다.");
        }

        if (errorObj.length() > 0) {
            resArr.put(errorObj);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(resArr.toString());
    }
}