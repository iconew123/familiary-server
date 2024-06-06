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
	    
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");

	    BufferedReader reader = request.getReader();
	    StringBuilder builder = new StringBuilder();
	    String line;

	    while ((line = reader.readLine()) != null) {
	    	builder.append(line);
	    }
	    

	    JSONObject jsonObj = new JSONObject(builder.toString());
	    String date = jsonObj.getString("date");

	    PrintWriter out = response.getWriter();
	    out.print("Date received: " + date);
	    out.flush();
	}

}
