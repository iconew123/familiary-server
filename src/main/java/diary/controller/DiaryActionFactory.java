package diary.controller;

import diary.controller.action.*;
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
        else if (command.equals("find"))
            action = new FindOneDiary();
        else if (command.equals("diarylist"))
            action = new SearchAllDiaries();
        else if (command.equals("imagelist"))
            action = new SearchAllImages();

        return action;
    }
}
