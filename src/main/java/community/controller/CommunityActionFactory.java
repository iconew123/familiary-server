package community.controller;

import community.controller.action.CreateCommunityAction;
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
		}

		return action;
	}
}
