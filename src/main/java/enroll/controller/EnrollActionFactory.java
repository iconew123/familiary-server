package enroll.controller;

import enroll.controller.action.ReadBabyCodeAction;
import util.Action;

public class EnrollActionFactory {

    private EnrollActionFactory() {

    }

    private static EnrollActionFactory instance = new EnrollActionFactory();
    public static EnrollActionFactory getInstance() {
        return instance;
    }

    public Action getAction(String command) {
        Action action = null;

        if(command.equals("giveCode")) {
            action = new ReadBabyCodeAction();
        }
        return action;
    }

}

