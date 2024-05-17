package user.controller;

import user.controller.action.LoginAction;

public class ActionFactory {
	
	// 액션 인스턴스 팩토리
	private ActionFactory() {
		
	}
	
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("login"))
			action = new LoginAction();
		
		return action;
	}
}
