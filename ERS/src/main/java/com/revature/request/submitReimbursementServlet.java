package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.ajax.ClientMessage;


@WebServlet("/ERS/submitReimbursementServlet")
@MultipartConfig //Allow servlet to recognize and support multipart/form-data needed for file upload
public class submitReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public submitReimbursementServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		
		//Pass request to controller
		Object data = RequestHelper.process(request);
		PrintWriter writer = response.getWriter();
		String htmlRespone = "";
				
		if(((ClientMessage) data).getMessage().equals("REIMBURSEMENET SUBMISSION SUCCESSFUL")) {
		
	        // build HTML code
			htmlRespone += "<html><body>";
			htmlRespone += "<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>";
	        htmlRespone += "<script>"
	        		+ "swal('Success','Reimbursment Submitted', 'success').then(()=> {window.location.href='/ERS/html/submitReimbursement.html'});"
	        		+ "</script>";
	        htmlRespone += "</body></html>";
	        
		} else if (((ClientMessage) data).getMessage().equals("REIMBURSEMENET SUBMISSION UNSUCCESSFUL")){
			htmlRespone += "<html><body>";
			htmlRespone += "<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>";
	        htmlRespone += "<script>"
	        		+ "swal('Oopss..','Something Went Wrong', 'error').then(()=> {window.location.href='/ERS/html/submitReimbursement.html'});"
	        		+ "</script>";      
	        htmlRespone += "</body></html>";
		} else if (((ClientMessage) data).getMessage().equals("REIMBURSEMENET SUBMISSION UNSUCCESSFUL NEGATIVE AMOUNT")){
			htmlRespone += "<html><body>";
			htmlRespone += "<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>";
	        htmlRespone += "<script>"
	        		+ "swal('Oopss..','Please enter a positive amount!', 'error').then(()=> {window.location.href='/ERS/html/submitReimbursement.html'});"
	        		+ "</script>";      
	        htmlRespone += "</body></html>";
		}
		
		//pass your response back
		writer.println(htmlRespone);
	}

}