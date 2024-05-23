package baby.controller;

import baby.controller.action.BabyCreateAction;
import baby.controller.action.BabyInfoAction;
import baby.controller.action.BabyUpdateAction;
import util.Action;

//	2. ActionFactory
//	- 액션을 만들어내는 공장
//	Ex. 사용자가 요청한 작업이 무엇인지 판별하고 해당하는 액션을 생성하여 실행

public class BabyActionFactory {
	
	// 기본 생성자
	private BabyActionFactory() {
		
	}
	
	// 싱클톤 디자인 패턴 사용
	private static BabyActionFactory instance = new BabyActionFactory();
	public static BabyActionFactory getInstance() {
		return instance;
	}
	
	// 클라이언트 요청에 따라 적절한 액션을 생성하여 반환하는 메서드
	// 현재는 'create'라는 명령에 대해 BabyCreateAction()을 생성하고 반환함.
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("create")) {
			action = new BabyCreateAction();
		} else if(command.equals("read")) {
			action = new BabyInfoAction();
		} else if(command.equals("update")) {
			action = new BabyUpdateAction();
		}
		
		return action;
	}

}
