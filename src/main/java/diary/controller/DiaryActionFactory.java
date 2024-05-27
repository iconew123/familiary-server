package diary.controller;

import diary.controller.action.CreateDiaryAction;
import diary.controller.action.DeleteDiaryAction;
import diary.controller.action.UpdateDiaryAction;
import util.Action;

public class DiaryActionFactory {

    private DiaryActionFactory() {

    }

    private static DiaryActionFactory instance = new DiaryActionFactory();

    public static DiaryActionFactory getInstance() {
        return instance;
    }

    public Action getAction(String command) {
        Action action = null;


        if (command.equals("create"))
            action = new CreateDiaryAction();
        else if (command.equals("update"))
            action = new UpdateDiaryAction();
        else if (command.equals("delete"))
            action = new DeleteDiaryAction();

        return action;
    }
}
