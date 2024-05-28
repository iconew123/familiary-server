package baby.controller.action;

import enroll.model.EnrollDao;
import enroll.model.EnrollRequestDto;
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

public class BabyEnrollAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        String method = request.getMethod();

        String user_id = null;
        String baby_code = null;
        String position = null;

        if(method.equals("POST")){
            Collection<Part> parts = request.getParts();

            for(Part part : parts) {
                String type = part.getContentType();
                String partName = part.getName();

                InputStream in = part.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String value = br.readLine();

                switch (partName) {
                    case "user_id":
                        user_id = value;
                        break;
                    case "baby_code":
                        baby_code = value;
                    case "position":
                        position = value;
                }
                br.close();
                in.close();
            }

            EnrollRequestDto enroll = new EnrollRequestDto(user_id, baby_code, position);
            EnrollDao enrollDao = new EnrollDao();
            enrollDao.createEnroll(enroll);

            // 결과를 응답하기
            JSONObject resObj = new JSONObject();
            resObj.put("status", 200);
            resObj.put("message", "아기가 성공적으로 등록되었습니다.");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf8");

            response.getWriter().append(resObj.toString());
        }
    }
}
