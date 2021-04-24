package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controller.EmployeeController;
import com.revature.controller.EmployeeControllerImpl;

public class EmployeeCountServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Pass request to controller
		HttpSession httpSession = request.getSession(false);
		if (httpSession != null) {
			Object data = RequestHelper.process(request);
			//EmployeeController employeeController = new EmployeeControllerImpl();
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((Integer) data);
		}
		else response.sendError(401);
	}
	
	
	
}
