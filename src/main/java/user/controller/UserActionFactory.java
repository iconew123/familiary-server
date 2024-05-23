package user.controller;

import util.Action;

public class UserActionFactory {

	private UserActionFactory() {

	}

	private static UserActionFactory instance = new UserActionFactory();

	public static UserActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction (String command) {
		Action action = null;
		
//		if (command.equals("join")) {
//			action = new JoinActionPro();
//		}
//		else if (command.equals("login")) {
//			action = new LoginActionPro();
//		}  
//		else if (command.equals("logout")) {
//			action = new LogoutActionPro();
//		} 
//		else if (command.equals("update")) {
//			action = new UpdateActionPro();
//		} 
//		else if (command.equals("delete")) {
//			action = new LoginActionPro();
//		}

		
		return action;
		
	}
	
}
