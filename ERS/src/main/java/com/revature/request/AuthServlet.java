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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";
		
		htmlResposne += "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"ISO-8859-1\">\r\n"
				+ "		<title>Login into ERS</title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<form action=\"/ERS/AuthServlet\" method=\"post\" name=\"signingIn\">\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<input type=\"email\" name=\"email\" placeholder=\"account email\" required/>\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<input type=\"password\" name=\"password\" placeholder=\"account password\" required/>\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<input type=\"submit\"  value=\"login\">\r\n"
				+ "			<button type = \"reset\" value = \"Reset\" >Reset</button>  \r\n"
				+ "		</form>\r\n"
				+ "	</body>\r\n"
				+ "</html>";
		
		//pass your response back
		writer.println(htmlResposne);
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
			if (employee.getIsManager())
				response.sendRedirect("html/mdash.html");
			else response.sendRedirect("html/edash.html");
		}
	}	
}
