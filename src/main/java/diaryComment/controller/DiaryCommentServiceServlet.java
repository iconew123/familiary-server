package diaryComment.controller;

import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DiaryCommentServiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인 허용
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        request.setCharacterEncoding("UTF-8");

        String command = request.getParameter("command");

        if(command != null) {
            DiaryCommentActionFactory dcaf = DiaryCommentActionFactory.getInstance();
            Action action = dcaf.getAction(command);

            if(action != null){
                action.execute(request, response);
            }else{
                response.sendError(404);
            }
        }else{
            response.sendError(404);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인 허용
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        request.setCharacterEncoding("UTF-8");

        String command = request.getParameter("command");

        if(command != null) {
            DiaryCommentActionFactory dcaf = DiaryCommentActionFactory.getInstance();
            Action action = dcaf.getAction(command);

            if(action != null){
                action.execute(request, response);
            }else{
                response.sendError(404);
            }
        }else{
            response.sendError(404);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
