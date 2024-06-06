package communityComment.controller;

import communityComment.controller.action.CreateCommunityCommentAction;
import communityComment.controller.action.DeleteCommunityCommentAction;
import communityComment.controller.action.ReadCommunityCommentAction;
import util.Action;

public class CommunityCommentActionFactory {
    private CommunityCommentActionFactory() {

    }

    private static CommunityCommentActionFactory instance = new CommunityCommentActionFactory();

    public static CommunityCommentActionFactory getInstance() {
        return instance;
    }

    public Action getAction(String command) {
        Action action = null;

        if(command.equals("writeComment")) {
            action = new CreateCommunityCommentAction();
        } else if(command.equals("deleteComment")) {
            action = new DeleteCommunityCommentAction();
        } else if(command.equals("readComment")) {
            action = new ReadCommunityCommentAction();
        }
        return action;
    }
}
