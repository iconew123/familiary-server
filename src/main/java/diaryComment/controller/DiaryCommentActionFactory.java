package diaryComment.controller;

import diaryComment.controller.action.CreateDiaryComment;
import util.Action;

public class DiaryCommentActionFactory {

    private DiaryCommentActionFactory(){

    }

    private static DiaryCommentActionFactory instance = new DiaryCommentActionFactory();

    public static DiaryCommentActionFactory getInstance(){
        return instance;
    }

    public Action getAction(String command){
        Action action = null;

        if(command.equals("create"))
            action = new CreateDiaryComment();

        return action;
    }
}
