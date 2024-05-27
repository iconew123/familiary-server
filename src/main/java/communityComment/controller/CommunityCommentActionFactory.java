package communityComment.controller;

import community.controller.CommunityActionFactory;
import communityComment.controller.action.CreateCommunityCommentAction;
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
        }
        return action;
    }
}
