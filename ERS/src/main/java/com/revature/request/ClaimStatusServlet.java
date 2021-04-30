package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClaimStatusServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			Object data = RequestHelper.process(request);
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(data);
		}
		else response.sendError(401);
	}

	
	
}
