package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ajax.ClientMessage;


@WebServlet("/ERS/submitReimbursementServlet")
@MultipartConfig //Allow servlet to recognize and support multipart/form-data needed for file upload
public class submitReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public submitReimbursementServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Pass request to controller
		Object data = RequestHelper.process(request);
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlRespone = "";
		
		if(((ClientMessage) data).getMessage().equals("REIMBURSEMENET SUBMISSION SUCCESSFUL")) {
			
	        // build HTML code
	        htmlRespone += "<html>";
	        htmlRespone += "<h2>Your reimbursement request was successfully submitted. It is pending approval by a finance manager.</h2>";    
	        htmlRespone += "</html>";
	        
		} else if (((ClientMessage) data).getMessage().equals("REIMBURSEMENET SUBMISSION UNSUCCESSFUL")){
			htmlRespone += "<html>";
	        htmlRespone += "<h2>Your reimbursement request was unsuccessfully submitted.</h2>";    
	        htmlRespone += "</html>";
		}
		
		//pass your response back
		writer.println(htmlRespone);
	}

}