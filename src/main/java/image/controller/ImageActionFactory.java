package image.controller;

import java.util.Arrays;

import image.controller.action.ImageListAction;
import util.Action;

public class ImageActionFactory {

    private ImageActionFactory() {
    }

    ;

    private static ImageActionFactory instance = new ImageActionFactory();

    public static ImageActionFactory getInstance() {
        return instance;
    }


    public Action getAction(String command, String method) {
        Action action = null;

        switch (method) {
            case "GET":
                String[] commands = command.split("/");
                System.out.println(Arrays.toString(commands));

                if (commands.length == 3) {
                    action = new ImageListAction(commands);
                } else if (commands.length == 2) {

                }
                break;

            case "POST":
//			action = new ImageRegistAction();
                break;

            case "DELETE":
                String[] commands2 = command.split("/");
                System.out.println(Arrays.toString(commands2));

                break;

            default:
                break;
        }

        if (command.equals(action)) {

        }

        return action;
    }

}
