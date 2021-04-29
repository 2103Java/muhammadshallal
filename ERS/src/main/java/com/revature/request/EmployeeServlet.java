package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ERS/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		String id = (String) httpSession.getAttribute("employeeId");
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";
		htmlResposne += "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"ISO-8859-1\">\r\n"
				+ "		<title>Employee services</title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<a href=\"/ERS/submitReimbursementServlet\">Submit a reimbursement request</a>\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "		<a href=\"/ERS/showEmployeeReimbursmentsServlet\">Show my previous reimbursements</a>\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "     <a href=\"/ERS/LogoutServlet\">Logout</a>\r\n"
				+ "	</body>\r\n"
				+ "</html>";
		
		//pass your response back
		writer.println(htmlResposne);
	}
	
	

}
