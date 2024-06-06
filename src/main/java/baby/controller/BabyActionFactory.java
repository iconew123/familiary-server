package baby.controller;

import baby.controller.action.*;
import util.Action;


public class BabyActionFactory {

	private BabyActionFactory() {
		
	}

	private static BabyActionFactory instance = new BabyActionFactory();
	public static BabyActionFactory getInstance() {
		return instance;
	}

	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("create")) {
			action = new BabyCreateAction();
		} else if(command.equals("enroll")){
			action = new BabyEnrollAction();
		} else if(command.equals("read")) {
			action = new BabyInfoAction();
		} else if(command.equals("update")) {
			action = new BabyUpdateAction();
		} else if(command.equals("delete")) {
			action = new BabyDeleteAction();
		}
		
		return action;
	}

}
