package baby.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        // 요청 파라미터에서 'date' 값을 읽음
        String date = request.getParameter("date");

        // 서버 콘솔에 날짜 출력
        System.out.println("Received date: " + date);

        // 클라이언트에 응답
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("Date received: " + date);
        out.flush();
	}

}
