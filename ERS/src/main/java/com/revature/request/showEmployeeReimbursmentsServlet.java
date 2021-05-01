package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.model.Reimbursment;

@WebServlet("/ERS/show/employee/reimbursments")
public class showEmployeeReimbursmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public showEmployeeReimbursmentsServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Pass request to controller
		Object fromController =  RequestHelper.process(request);
		List<Reimbursment> previousReimbursements = (List<Reimbursment>) fromController;
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";

		htmlResposne += "<thead class=\"text-primary\">";
		htmlResposne += "<tr>";
		htmlResposne += "<th>Submission date</th>";
		htmlResposne += "<th>Type</th>";
		htmlResposne += "<th>Status</th>";
		htmlResposne += "<th>Amount</th>";
		htmlResposne += "<th>Description</th>";
		htmlResposne += "</tr>";
		htmlResposne += "</thead>";
		
		htmlResposne += "<tbody>";
		for(int i = 0; i < previousReimbursements.size(); i++) {
			
			htmlResposne += "<tr>";
			htmlResposne += "<td>";
			htmlResposne += previousReimbursements.get(i).getSubmissionDate();
			htmlResposne += "</td>";
			htmlResposne += "<td>";
			htmlResposne += previousReimbursements.get(i).getType();
			htmlResposne += "</td>";
			htmlResposne += "<td>";
			htmlResposne += previousReimbursements.get(i).getStatus();
			htmlResposne += "</td>";
			htmlResposne += "<td>";
			htmlResposne += previousReimbursements.get(i).getAmount();
			htmlResposne += "</td>";
			htmlResposne += "<td>";
			htmlResposne += previousReimbursements.get(i).getDescription();
			htmlResposne += "</td>";
			htmlResposne += "</tr>";
		}
		
		//pass your response back
		writer.println(htmlResposne);
	}

}