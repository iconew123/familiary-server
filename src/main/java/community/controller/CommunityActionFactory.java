package community.controller;

import community.controller.action.CreateCommunityAction;
import community.controller.action.ReadCommunityByNoticeAction;
import community.controller.action.ReadCommunityByRecommendAction;
import community.controller.action.ReadCommunityByTalkAction;
import community.controller.action.UpdateCommunityAction;
import util.Action;

public class CommunityActionFactory {
	private CommunityActionFactory() {

	}

	private static CommunityActionFactory instance = new CommunityActionFactory();

	public static CommunityActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;

		if (command.equals("write")) {
			action = new CreateCommunityAction();
		} else if (command.equals("read/talk")) {
			action = new ReadCommunityByTalkAction();
		} else if (command.equals("read/notice")) {
			action = new ReadCommunityByNoticeAction();
		} else if (command.equals("read/recommend")) {
			action = new ReadCommunityByRecommendAction();
		} else if (command.equals("update")) {
			action = new UpdateCommunityAction();
		}

		return action;
	}
}
