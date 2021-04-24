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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = req.getSession(false);
		if (httpSession != null) {
			EmployeeController employeeController = new EmployeeControllerImpl();
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(employeeController.getEmployeeCount());
		}
		else resp.sendError(401);
	}
	
	
	
}
