package diary.controller;

import diary.controller.action.CreateDiaryAction;

public class DiaryActionFactory {
	
	private DiaryActionFactory() {
	
	}
	
	private static DiaryActionFactory instance = new DiaryActionFactory();
	
	public static DiaryActionFactory getInstance() {
		return instance;
	}
	
	public DiaryAction getAction(String diaryCommand) {
		DiaryAction action = null;
		
		if(diaryCommand.equals("createDiary"))
			action = new CreateDiaryAction();
		
		return action;
	}
}
