package user.controller.action;

import org.json.JSONObject;
import user.model.UserDao;
import user.model.UserRequestDto;
import user.model.UserResponseDto;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equals("POST")) {
            String id = request.getParameter("id");
            String password = request.getParameter("password");

            boolean isValid = true;
            if (password == null || password.equals(""))
                isValid = false;

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            JSONObject resObj = new JSONObject();

            if (isValid) {
                UserDao userDao = UserDao.getInstance();
                UserResponseDto user = userDao.findUserByIdAndPassword(id,password);
                UserRequestDto userDto = new UserRequestDto();

                userDto.setId(user.getId());
                userDto.setPassword(password);

                boolean result = userDao.deleteUser(userDto);

                if (result) {
                    resObj.put("status", 200);
                    resObj.put("message", "User updated successfully.");

                } else {
                    response.sendError(400);
                    resObj.put("status", 400);
                    resObj.put("message", "No Existed User");
                }

            } else {
                response.sendError(500);
                resObj.put("status", 500);
                resObj.put("message", "Database Error");
            }
            response.getWriter().append(resObj.toString());
        }
    }
}