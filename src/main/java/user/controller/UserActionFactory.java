package user.controller;

import user.controller.action.*;
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
		
		if (command.equals("join")) {
			action = new JoinAction();
		}
		else if (command.equals("login")) {
			action = new LoginAction();
		}  
		else if (command.equals("logout")) {
			action = new LogoutAction();
		} 
		else if (command.equals("update")) {
			action = new UpdateAction();
		} 
		else if (command.equals("delete")) {
			action = new DeleteAction();
		}
		else if (command.equals("checkDuplicate")) {
			action = new CheckDuplicate();
		}
		else if (command.equals("verifyPassword")) {
			action = new VerifyPassword();
		}
		else if (command.equals("checkDeleteBaby")) {
			action = new CheckDeleteBabyAction();
		}


		return action;
		
	}
	
}
