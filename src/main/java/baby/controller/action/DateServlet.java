package baby.controller.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		// CROS 정책을 허용하여 모든 도메인에서 접근가능하도록 설정
		response.setHeader("Access-Control-Allow-Origin", "*");

	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");

	    // 클라이언트로부터 전송된 데이터를 읽어옴
	    BufferedReader reader = request.getReader();
	    StringBuilder builder = new StringBuilder();
	    String line;
	    // 한줄씩 읽어와(readLine()) StringBuilder에 추가
	    while ((line = reader.readLine()) != null) {
	    	builder.append(line);
	    }
	    
	    // 받은 JSON 데이터를 파싱해 JSONObject로 변환
	    JSONObject jsonObj = new JSONObject(builder.toString());
	    // JSON 객체에서 'date' 필드 값 추출
	    String date = jsonObj.getString("date");

	    // 확인용
	    System.out.println("date: " + date);

	    // 클라이언트에 응답 보냄
	    PrintWriter out = response.getWriter();
	    // 받은 날짜를 포함한 메세지를 클라이언트에 응답으로 전송
	    out.print("Date received: " + date);
	    out.flush();
	}

}
