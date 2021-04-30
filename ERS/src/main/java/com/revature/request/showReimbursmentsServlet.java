package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResponse = "";
		htmlResponse += "<html>\r\n"
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
		writer.println(htmlResponse);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Original filter from doPost in showReimbursmentsServlet" + request.getParameter("filter"));		
		//Pass request to controller
		Object fromController =  RequestHelper.process(request);
		List<Reimbursment> previousReimbursements = (List<Reimbursment>) fromController;
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResponse = "";
		
		if(previousReimbursements.size() == 0) {
			// build HTML code
	        htmlResponse += "<html>";
	        htmlResponse += "<h2>There are no reimbursement requests.</h2>";    
	        htmlResponse += "</html>";
		} else {
			htmlResponse += "<html>";
			htmlResponse += "<h2>Here are ";
			htmlResponse += request.getParameter("filter").toLowerCase();
			htmlResponse += " reimbursement requests.</h2>"; 
			
			
			htmlResponse += "<table>";
			htmlResponse += "<thead>";
			htmlResponse += "<tr>";
			htmlResponse += "<th>Ticket Id</th>";
			htmlResponse += "<th>Employee Email</th>";
			htmlResponse += "<th>Amount</th>";
			htmlResponse += "<th>Type</th>";
			htmlResponse += "<th>Status</th>";
			htmlResponse += "<th>Submission date</th>";
			htmlResponse += "<th>Description</th>";
			htmlResponse += "</tr>";
			htmlResponse += "</thead>";
			
			htmlResponse += "<tbody>";
			for(int i = 0; i < previousReimbursements.size(); i++) {
				
				htmlResponse += "<tr>";
				htmlResponse += "<td>";
				htmlResponse += previousReimbursements.get(i).getId();
				htmlResponse += "</td>";
				htmlResponse += "<td>";
				htmlResponse += previousReimbursements.get(i).getEmployeeId();
				htmlResponse += "</td>";
				htmlResponse += "<td>";
				htmlResponse += previousReimbursements.get(i).getAmount();
				htmlResponse += "</td>";
				htmlResponse += "<td>";
				htmlResponse += previousReimbursements.get(i).getType();
				htmlResponse += "</td>";
				if(previousReimbursements.get(i).getStatus().equals("pending")) {
					htmlResponse += "<td>";
					htmlResponse += "<form method=\"post\" action=\"/ERS/FinanceManagerApproveDeny\" name=\"fmdecision\">";
					htmlResponse += "<select name=\"selection\" onchange=\"getValue(this)\">\r\n"
							+ "		              <option value=\"pending\">Keep it for now</option>\r\n"
							+ "		              <option value=\"approved\">Approve it</option>\r\n"
							+ "		              <option value=\"denied\">Deny it</option>\r\n"
							+ "		            </select>";
					htmlResponse += "<input type=\"hidden\" id=\"ticketId\" name=\"ticketId\" value=";
					htmlResponse += previousReimbursements.get(i).getId();
					htmlResponse += ">";
					htmlResponse += "<button type=\"submit\" form=\"fmdecision\">change status</button>";
					
					htmlResponse += "</form>";
					htmlResponse += "</td>";
				} else {
					htmlResponse += "<td>";
					htmlResponse += previousReimbursements.get(i).getStatus();
					htmlResponse += "</td>";
				}
				htmlResponse += "<td>";
				htmlResponse += previousReimbursements.get(i).getSubmissionDate();
				htmlResponse += "</td>";
				htmlResponse += "<td>";
				htmlResponse += previousReimbursements.get(i).getDescription();
				htmlResponse += "</td>";
				htmlResponse += "</tr>";
			}
			htmlResponse += "</tbody>";
			htmlResponse += "</table>";
			htmlResponse += "</html>";
		}
		
		//pass your response back
		writer.println(htmlResponse);
	}

}
