package babyInfo.controller;

import babyInfo.controller.action.*;
import util.Action;



public class BabyInfoActionFactory {

    private BabyInfoActionFactory() {

    }

    private static BabyInfoActionFactory instance = new BabyInfoActionFactory();
    public static BabyInfoActionFactory getInstance() {
        return instance;
    }

    public Action getAction(String command) {
        Action action = null;

        if(command.equals("create")) {
            action = new BabyInfoCreateAction();
        }
        else if(command.equals("read")) {
            action = new BabyInfoReadAction();
        }
        else if(command.equals("update")) {
            action = new BabyInfoUpdateAction();
        }
        else if(command.equals("delete")) {
            action = new BabyInfoDeleteAction();
        }
        else if(command.equals("allInfo")) {
            action = new SearchAllBabyInfo();
        }

        return action;
    }

}

