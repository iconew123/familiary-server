package enroll.controller;

import baby.controller.action.*;
import babyInfo.controller.action.BabyInfoCreateAction;
import babyInfo.controller.action.BabyInfoDeleteAction;
import babyInfo.controller.action.BabyInfoReadAction;
import babyInfo.controller.action.BabyInfoUpdateAction;
import enroll.controller.action.ReadBabyCodeAction;
import util.Action;

//	2. ActionFactory
//	- 액션을 만들어내는 공장
//	Ex. 사용자가 요청한 작업이 무엇인지 판별하고 해당하는 액션을 생성하여 실행

public class EnrollActionFactory {

    // 기본 생성자
    private EnrollActionFactory() {

    }

    // 싱클톤 디자인 패턴 사용
    private static EnrollActionFactory instance = new EnrollActionFactory();
    public static EnrollActionFactory getInstance() {
        return instance;
    }

    // 클라이언트 요청에 따라 적절한 액션을 생성하여 반환하는 메서드
    // 현재는 'create'라는 명령에 대해 BabyCreateAction()을 생성하고 반환함.
    public Action getAction(String command) {
        Action action = null;

        if(command.equals("giveCode")) {
            action = new ReadBabyCodeAction();
        }
        return action;
    }

}

