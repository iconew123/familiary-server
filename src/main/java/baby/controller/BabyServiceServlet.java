package baby.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//	3. ServiceServlet
//	- 웹 애플리케이션에서 클라이언트 요청을 받아들이고 그에 맞는 서비스(또는 액션)을 실행하는 역할
//	- 클라이언트가 서버에 요청 -> 서비스 서블릿이 요청을 받아들이고 어떤 작업을 해야할지 결정 해 해당하는 액션을 실행

// 멀티파트 요청을 처리할 수 있도록 지정
@MultipartConfig
public class BabyServiceServlet extends HttpServlet {
	
	// doGet : 클라이언트로부터의 HTTP GET 요청을 처리하는 메서드
	// 클라이언트로부터 전송된 command 파라미터를 확인하여 해당하는 액션을 실행함
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 클라이언트 요청에서 command라는 이름의 파라미터 값을 가져옴
		// 이 파라미터는 클라이언트가 어떤 작업을 요청했는지 나타냄
		String command = request.getParameter("command");
		// 클라이언트가 어떤 작업을 요청했는지 확인
		System.out.println("/baby?command=" + command);
		
		// 요청된 URL 정보 경로 확인
		System.out.println("request.getPathInfo() : " + request.getPathInfo());
		
		// command가 업석나 잘못된 경우 처리
		if(command != null) {
			// BabyActionFactory를 가져와 액션을 생성할 수 있음
			BabyActionFactory af = BabyActionFactory.getInstance();
			// BabyActionFactory를 사용해 요청된 command에 해당하는 액션을 가져옴
			Action action = af.getAction(command);
			
			// 요청된 액션이 있는지 확인하여 액션 존재 시 해당 액션 실행, 없으면 404 오류 반환
			if(action != null) {
				action.execute(request, response);
			} else {
				response.sendError(404);
			}			
		} else {
			response.sendError(404);			
		}
	}
	
	// 클라이언트로부터 HTTP POST 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	// 클라이언트로부터 HTTP DELETE 요청을 처리하는 메서드
	// 요청 데이터의 문자 인코딩을 UTF-8로 설정한 후 doGet을 호출해 GET 요청과 동일하게 처리
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}