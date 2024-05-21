package user.controller;

public class UserActionFactory {

	private UserActionFactory() {

	}

	private static UserActionFactory instance = new UserActionFactory();

	public static UserActionFactory getInstance() {
		return instance;
	}

	public UserAction getAction (String command) {
		UserAction action = null;
		
		if (command.equals("")) {
			
		}
		
		return action;
		
	}
	
}
