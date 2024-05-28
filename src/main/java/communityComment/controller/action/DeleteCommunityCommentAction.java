package communityComment.controller.action;

import community.model.CommunityDao;
import community.model.CommunityRequestDto;
import communityComment.model.CommunityCommentDao;
import communityComment.model.CommunityCommentRequestDto;
import org.json.JSONObject;
import util.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteCommunityCommentAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        CommunityCommentDao communityCommentDao = CommunityCommentDao.getInstance();

        // String code = session.getAttribute("code");
        String code = request.getParameter("code");
        String userId = request.getParameter("userId");

        CommunityCommentRequestDto communityComment = new CommunityCommentRequestDto(code, userId);
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
