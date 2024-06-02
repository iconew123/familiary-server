package community.controller;

import community.controller.action.CreateCommunityAction;
import community.controller.action.DeleteCommunityAction;
import community.controller.action.ReadCommunityByNoticeAction;
import community.controller.action.ReadCommunityByRecommendAction;
import community.controller.action.ReadCommunityByTalkAction;
import community.controller.action.ReadCommunityDetailAction;
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

		if (command.equals("create")) {
			action = new CreateCommunityAction();
		} else if (command.equals("read/chat")) {
			action = new ReadCommunityByTalkAction();
		} else if (command.equals("read/notice")) {
			action = new ReadCommunityByNoticeAction();
		} else if (command.equals("read/recommend")) {
			action = new ReadCommunityByRecommendAction();
		} else if (command.equals("update")) {
			action = new UpdateCommunityAction();
		} else if (command.equals("delete")) {
			action = new DeleteCommunityAction();
		} else if (command.equals("read/detail")) {
			action = new ReadCommunityDetailAction();
		}

		return action;
	}
}
