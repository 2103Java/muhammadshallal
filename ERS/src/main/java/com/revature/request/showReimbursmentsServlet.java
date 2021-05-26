package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Reimbursment;

@WebServlet("/ERS/all/claim")
public class showReimbursmentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public showReimbursmentsServlet() {
        super();
    }
    
    public Set<String> attributes(HttpServletRequest servletRequest) {
    	  Set<String> attrList = new HashSet<>();
    	  Enumeration<String> attributes = (Enumeration<String>) servletRequest.getAttributeNames();
    	  while (attributes.hasMoreElements()) {
    	    attrList.add(attributes.nextElement());
    	  }
    	  return attrList;
    	}
    
    public Set<String> attributes(HttpSession httpSession) {
  	  Set<String> attrList = new HashSet<>();
  	  Enumeration<String> attributes = (Enumeration<String>) httpSession.getAttributeNames();
  	  while (attributes.hasMoreElements()) {
  	    attrList.add(attributes.nextElement());
  	  }
  	  return attrList;
  	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		
		if(!attributes(httpSession).contains("curFilter")) {
			httpSession.setAttribute("curFilter", request.getParameter("filter"));
		} else {
			request.setAttribute("filter", httpSession.getAttribute("curFilter"));
		}
		
		//Pass request to controller
		Object fromController =  RequestHelper.process(request);
		List<Reimbursment> previousReimbursements = (List<Reimbursment>) fromController;
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResponse = "";
		
		htmlResponse += "<thead class=\"text-primary\">";
		htmlResponse += "<tr>";
		htmlResponse += "<th>ID</th>";
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
			htmlResponse += "<td>";
			htmlResponse += previousReimbursements.get(i).getStatus();
			htmlResponse += "</td>";
			htmlResponse += "<td>";
			htmlResponse += previousReimbursements.get(i).getSubmissionDate();
			htmlResponse += "</td>";
			htmlResponse += "<td>";
			htmlResponse += previousReimbursements.get(i).getDescription();
			htmlResponse += "</td>";
			htmlResponse += "</tr>";
		}
		htmlResponse += "</tbody>";
		
		//pass your response back
		writer.println(htmlResponse);
	}

}