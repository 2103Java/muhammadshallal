package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controller.LoginController;
import com.revature.controller.LoginControllerImpl;
import com.revature.model.Employee;

public class AuthServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LoginController loginController = new LoginControllerImpl();
		Employee employee = loginController.authenticate(req.getParameter("email"), req.getParameter("password"));
		if (employee == null)
			resp.sendError(401);
		else {
			if (employee.isManager())
				resp.sendRedirect("mdash.html");
			else resp.sendRedirect("edash.html");
		}
	}

	
	
}
