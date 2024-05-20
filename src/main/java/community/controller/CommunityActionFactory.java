package community.controller;

public class CommunityActionFactory {
	private CommunityActionFactory() {

	}

	private static CommunityActionFactory instance = new CommunityActionFactory();

	public static CommunityActionFactory getInstance() {
		return instance;
	}

	public CommunityAction getAction(String command) {
		CommunityAction action = null;

		return action;
	}
}
