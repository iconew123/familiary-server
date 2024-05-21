package baby.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

//	1. Action 
//	- 클라이언트 → 서버에 보낸 요청에 대한 처리 방법 정의
//	Ex. 사용자의 로그인 시도 시, 로그인 액션은 사용자가 제공한 정보를 검사하고 인증하는 등의 작업을 수행

	
//	excute 메서드 : 실제로 클라이언트 요청을 처리하고 응답을 생성함
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
