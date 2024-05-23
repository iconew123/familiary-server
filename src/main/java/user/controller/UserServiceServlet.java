package user.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.controller.UserActionFactory;
import util.Action;

@MultipartConfig
public class UserServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServiceServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		System.out.println("GET command" + command);

		if (command != null) {
			UserActionFactory daf = UserActionFactory.getInstance();
			Action action = daf.getAction(command);

			if (action != null) {
				action.execute(request, response);
			} else {
				response.sendError(400);
				System.out.println("No Existed User");
			}
		} else {
			response.sendError(500);
			System.out.println("Database Error");
		}

//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");
		System.out.println("GET command" + command);

		if (command != null) {
			UserActionFactory daf = UserActionFactory.getInstance();
			Action action = daf.getAction(command);

			if (action != null) {
				action.execute(request, response);
			} else {
				response.sendError(400);
				System.out.println("No Existed User");
			}
		} else {
			response.sendError(500);
			System.out.println("Database Error");
		}

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String command = request.getParameter("command");
		System.out.println("GET command" + command);

		if (command != null) {
			UserActionFactory daf = UserActionFactory.getInstance();
			Action action = daf.getAction(command);

			if (action != null) {
				action.execute(request, response);
			} else {
				response.sendError(400);
				System.out.println("No Existed User");
			}
		} else {
			response.sendError(500);
			System.out.println("Database Error");
		}

	}

}
