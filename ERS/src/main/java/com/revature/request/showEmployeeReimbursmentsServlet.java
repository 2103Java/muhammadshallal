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

import com.revature.ajax.ClientMessage;
import com.revature.model.Reimbursment;

@WebServlet("/ERS/showEmployeeReimbursmentsServlet")
public class showEmployeeReimbursmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public showEmployeeReimbursmentsServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pass request to controller
		Object fromController =  RequestHelper.process(request);
		List<Reimbursment> previousReimbursements = (List<Reimbursment>) fromController;
		
		System.out.println("You have " + previousReimbursements.size() + " previous reimbursements.");
		System.out.println(previousReimbursements.toString());
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlRespone = "";
		
		if(previousReimbursements.size() == 0) {
			// build HTML code
	        htmlRespone += "<html>";
	        htmlRespone += "<h2>You don't have any previous reimbursement requests.</h2>";    
	        htmlRespone += "</html>";
		} else {
			htmlRespone += "<html>";
			htmlRespone += "<h2>Here are your previous reimbursement requests.</h2>"; 
			htmlRespone += "<table>";
			
			htmlRespone += "<thead>";
			htmlRespone += "<tr>";
			htmlRespone += "<th>Amount</th>";
			htmlRespone += "<th>Type</th>";
			htmlRespone += "<th>Status</th>";
			htmlRespone += "<th>Submission date</th>";
			htmlRespone += "<th>Description</th>";
			htmlRespone += "</tr>";
			htmlRespone += "</thead>";
			
			htmlRespone += "<tbody>";
			for(int i = 0; i < previousReimbursements.size(); i++) {
				
				htmlRespone += "<tr>";
				htmlRespone += "<td>";
				htmlRespone += previousReimbursements.get(i).getAmount();
				htmlRespone += "</td>";
				htmlRespone += "<td>";
				htmlRespone += previousReimbursements.get(i).getType();
				htmlRespone += "</td>";
				htmlRespone += "<td>";
				htmlRespone += previousReimbursements.get(i).getStatus();
				htmlRespone += "</td>";
				htmlRespone += "<td>";
				htmlRespone += previousReimbursements.get(i).getSubmissionDate();
				htmlRespone += "</td>";
				htmlRespone += "<td>";
				htmlRespone += previousReimbursements.get(i).getDescription();
				htmlRespone += "</td>";
				htmlRespone += "</tr>";
			}
			htmlRespone += "</tbody>";
			htmlRespone += "</table>";
			htmlRespone += "</html>";
		}
		
		//pass your response back
		writer.println(htmlRespone);
	}

}