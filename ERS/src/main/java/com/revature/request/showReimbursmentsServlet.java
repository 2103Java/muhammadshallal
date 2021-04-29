package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ajax.ClientMessage;
import com.revature.model.Reimbursment;

@WebServlet("/ERS/showReimbursmentsServlet")
public class showReimbursmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public showReimbursmentsServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession httpSession = request.getSession(false);
		String id = (String) httpSession.getAttribute("employeeId");
		System.out.println("id from session in doGet showReimbursmentsServlet:" + id);
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";
		htmlResposne += "<html>\r\n"
				+ "<head>\r\n"
				+ "	<meta charset=\"ISO-8859-1\">\r\n"
				+ "	<title>Previous reimbursements</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<form action=\"/ERS/showReimbursmentsServlet\" method=\"post\" name=\"submissionForm\">\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<p>Specify which status to show:</p>\r\n"
				+ "			<label for=\"all\">All</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"all\">\r\n"
				+ "			<label for=\"pending\">Pending</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"pending\">\r\n"
				+ "			<label for=\"approved\">Approved</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"approved\">\r\n"
				+ "			<label for=\"denied\">Denied</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"denied\">\r\n"
				+ "			<label for=\"Lodging\">Lodging</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"Lodging\">\r\n"
				+ "			<label for=\"Travel\">Travel</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"Travel\">\r\n"
				+ "			<label for=\"Food\">Food</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"Food\">\r\n"
				+ "			<label for=\"Other\">Other</label>\r\n"
				+ "			<input type=\"radio\" name=\"filter\" value=\"Other\">\r\n"
				+ "			\r\n"
				+ "			<input type=\"submit\" value=\"Submit\">\r\n"
				+ "	</form>\r\n"
				+ "	\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "     <a href=\"/ERS/LogoutServlet\">Logout</a>\r\n"
				+ "</body>\r\n"
				+ "</html>";
				
		
				
		//pass your response back
		writer.println(htmlResposne);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		String id = (String) httpSession.getAttribute("employeeId");
		System.out.println("id from session in doPost showReimbursmentsServlet:" + id);
		//Pass request to controller
		Object fromController =  RequestHelper.process(request);
		List<Reimbursment> previousReimbursements = (List<Reimbursment>) fromController;
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";
		
		if(previousReimbursements.size() == 0) {
			// build HTML code
	        htmlResposne += "<html>";
	        htmlResposne += "<h2>There are no reimbursement requests.</h2>";    
	        htmlResposne += "</html>";
		} else {
			htmlResposne += "<html>";
			htmlResposne += "<h2>Here are ";
			htmlResposne += request.getParameter("filter").toLowerCase();
			htmlResposne += " reimbursement requests.</h2>"; 
			htmlResposne += "<table>";
			
			htmlResposne += "<thead>";
			htmlResposne += "<tr>";
			htmlResposne += "<th>Employee Email</th>";
			htmlResposne += "<th>Amount</th>";
			htmlResposne += "<th>Type</th>";
			htmlResposne += "<th>Status</th>";
			htmlResposne += "<th>Submission date</th>";
			htmlResposne += "<th>Description</th>";
			htmlResposne += "</tr>";
			htmlResposne += "</thead>";
			
			htmlResposne += "<tbody>";
			for(int i = 0; i < previousReimbursements.size(); i++) {
				
				htmlResposne += "<tr>";
				htmlResposne += "<td>";
				htmlResposne += previousReimbursements.get(i).getEmployeeId();
				htmlResposne += "</td>";
				htmlResposne += "<td>";
				htmlResposne += previousReimbursements.get(i).getAmount();
				htmlResposne += "</td>";
				htmlResposne += "<td>";
				htmlResposne += previousReimbursements.get(i).getType();
				htmlResposne += "</td>";
				htmlResposne += "<td>";
				htmlResposne += previousReimbursements.get(i).getStatus();
				htmlResposne += "</td>";
				htmlResposne += "<td>";
				htmlResposne += previousReimbursements.get(i).getSubmissionDate();
				htmlResposne += "</td>";
				htmlResposne += "<td>";
				htmlResposne += previousReimbursements.get(i).getDescription();
				htmlResposne += "</td>";
				htmlResposne += "</tr>";
			}
			htmlResposne += "</tbody>";
			htmlResposne += "</table>";
			htmlResposne += "</html>";
		}
		
		//pass your response back
		writer.println(htmlResposne);
	}

}