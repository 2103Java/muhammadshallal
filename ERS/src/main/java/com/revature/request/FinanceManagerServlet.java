package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ERS/FinanceManagerServlet")
public class FinanceManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FinanceManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";
		htmlResposne += "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"ISO-8859-1\">\r\n"
				+ "		<title>Finance manager services</title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "		<a href=\"/ERS/showReimbursmentsServlet\">Show reimbursements</a>\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "     <a href=\"/ERS/LogoutServlet\">Logout</a>\r\n"
				+ "	</body>\r\n"
				+ "</html>";
		
		
		//pass your response back
		writer.println(htmlResposne);
				
	}
}
