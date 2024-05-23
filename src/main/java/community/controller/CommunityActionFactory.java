package community.controller;

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

		return action;
	}
}
