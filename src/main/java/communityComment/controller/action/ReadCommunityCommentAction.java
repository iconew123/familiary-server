package communityComment.controller.action;

import communityComment.model.CommunityCommentDao;
import communityComment.model.CommunityCommentResponseDto;
import org.json.JSONArray;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReadCommunityCommentAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인 허용
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        CommunityCommentDao communityCommentDao = CommunityCommentDao.getInstance();

        String communityCode = request.getParameter("communityCode");
        int code = Integer.parseInt(communityCode);
        List<CommunityCommentResponseDto> commentList = communityCommentDao.readAllCommunityComment(code);

        JSONArray resObj = new JSONArray(commentList);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().append(resObj.toString());
    }
}
