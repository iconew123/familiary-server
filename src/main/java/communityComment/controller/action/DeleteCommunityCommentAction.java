package communityComment.controller.action;

import communityComment.model.CommunityCommentDao;
import communityComment.model.CommunityCommentRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommunityCommentAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");

        request.setCharacterEncoding("UTF-8");
        CommunityCommentDao communityCommentDao = CommunityCommentDao.getInstance();

        String code = request.getParameter("commentCode");

        CommunityCommentRequestDto communityComment = new CommunityCommentRequestDto(code);
        boolean success = communityCommentDao.deleteCommunityComment(communityComment);

        JSONObject resObj = new JSONObject();

        if(success) {
            resObj.put("status", 200);
            resObj.put("message", "게시물이 삭제되었습니다.");
        } else {
            resObj.put("status", 500);
            resObj.put("message", "게시물 삭제에 실패했습니다.");
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(resObj.toString());
    }
}
