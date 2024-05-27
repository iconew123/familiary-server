package communityComment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Action;

public class CommunityCommentServiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String command = request.getParameter("command"); // input >> hidden 으로 가져옴
        String method = request.getMethod();

        System.out.println("command : " + command);
        System.out.println("method : " + method);

        // 요청된 URL 정보 경로 확인
        System.out.println("request.getPathInfo() : " + request.getPathInfo());

        CommunityCommentActionFactory af = CommunityCommentActionFactory.getInstance();
        Action action = af.getAction(command);

        if (action != null) {
            action.execute(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

}
