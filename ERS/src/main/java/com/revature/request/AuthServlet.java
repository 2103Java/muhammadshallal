package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Employee;

@WebServlet("/ERS/auth")
public class AuthServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public AuthServlet() {
        super();
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object data = RequestHelper.process(request);

		if (data == null) {
			PrintWriter writer = response.getWriter();
			String htmlResposne = "";
			htmlResposne += "<html><body>";
			htmlResposne += "<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>";
			htmlResposne += "<script>"
	        		+ "swal('Oopss','Wrong Email or Password, Try Again', 'error').then(()=> {window.location.href='/ERS/html/login.html'});"
	        		+ "</script>";
			htmlResposne += "</body></html>";
			writer.println(htmlResposne);
		}
		else {
			Employee employee = ((Employee) data);
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("employeeId", employee.getEmail());
			if (employee.getIsManager())
				response.sendRedirect("html/mdash.html");
			else response.sendRedirect("html/edash.html");
		}
	}	
}
