package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;

@WebServlet("/ERS/AuthServlet")
public class AuthServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public AuthServlet() {
        super();
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Object data = RequestHelper.process(request);

		if (data == null)
			response.sendError(401);
		else {
			Employee employee = ((Employee) data);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("employeeId", employee.getEmail());
			if (employee.getIsManager()) {
				response.sendRedirect("/ERS/FinanceManagerServlet");
			}
			else {
				response.sendRedirect("/ERS/EmployeeServlet");
			}
		}
	}	
}
