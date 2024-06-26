package diaryComment.controller;

import diaryComment.controller.action.CreateDiaryComment;
import diaryComment.controller.action.DeleteDiaryComment;
import diaryComment.controller.action.SearchAllComment;
import diaryComment.controller.action.UpdateDiaryComment;
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
        else if(command.equals("update"))
            action = new UpdateDiaryComment();
        else if(command.equals("delete"))
            action = new DeleteDiaryComment();
        else if(command.equals("find"))
            action = new SearchAllComment();

        return action;
    }
}
