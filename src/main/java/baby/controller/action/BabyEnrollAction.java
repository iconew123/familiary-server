package baby.controller.action;

import baby.model.Baby;
import baby.model.BabyDao;
import enroll.model.EnrollDao;
import enroll.model.EnrollRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BabyEnrollAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        String method = request.getMethod();

        String user_id = null;
        String baby_code = null; // 여기서 baby_code를 GET 매개변수로부터 받아옵니다.
        String position = null;

        if (method.equals("POST")) {
            user_id = request.getParameter("user_id");
            position = request.getParameter("position");
            baby_code = request.getParameter("baby_code");

            BabyDao dao = new BabyDao();
            Baby baby = dao.findBabyByCode(baby_code);

            JSONObject resObj = new JSONObject();
            resObj.put("baby", baby);

            if (baby == null) {
                resObj.put("message", "존재하지 않는 코드입니다.");
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                EnrollRequestDto enroll = new EnrollRequestDto(user_id, baby_code, position);
                EnrollDao enrollDao = new EnrollDao();

                boolean dupl = enrollDao.checkDupl(baby_code, user_id);
                resObj.put("dupl", dupl);
                if(dupl){
                    resObj.put("message", "이미 등록되어 있습니다.");
                }

                boolean exists = false;
                if(position.equals("mother")) {
                    exists = enrollDao.checkMother(baby_code);
                } else if(position.equals("father")){
                    exists = enrollDao.checkFather(baby_code);
                }

                resObj.put("exists", exists);
                System.out.println("여부: " + exists);
                if (exists) {
                    resObj.put("message", "해당 포지션이 이미 존재합니다.");
//                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                } else {
                    enrollDao.createEnroll(enroll);
                    resObj.put("message", "아기가 성공적으로 등록되었습니다.");
                    response.setStatus(HttpServletResponse.SC_OK);
                }

            }

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf8");

            response.getWriter().append(resObj.toString());
        }
    }
}
