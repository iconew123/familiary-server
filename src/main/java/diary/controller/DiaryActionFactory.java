package diary.controller;

import diary.controller.action.CreateDiaryAction;
import diary.controller.action.UpdateDiaryAction;

public class DiaryActionFactory {

	private DiaryActionFactory() {
	
	}
	
	private static DiaryActionFactory instance = new DiaryActionFactory();
	
	public static DiaryActionFactory getInstance() {
		return instance;
	}
	
	public DiaryAction getAction(String command) {
		DiaryAction action = null;
		

		if(command.equals("create"))
			action = new CreateDiaryAction();
		else if(command.equals("update"))
			action = new UpdateDiaryAction();
		
		return action;
	}
}
