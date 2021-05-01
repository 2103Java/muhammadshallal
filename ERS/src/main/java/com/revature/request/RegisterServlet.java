package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ajax.ClientMessage;

@WebServlet("/ERS/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pass request to controller
		Object data = RequestHelper.process(request);
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlRespone = "";
		String controllerPosition = ((ClientMessage) data).getMessage();
		if (((ClientMessage) data).getMessage().equals("REGISTRATION SUCCESSFUL")){
			htmlRespone += "<html><body>";
			htmlRespone += "<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>";
	        htmlRespone += "<script>"
	        		+ "swal('Success','Registered Successfully', 'success').then(()=> {window.location.href='/ERS/html/login.html'});"
	        		+ "</script>";
	        htmlRespone += "</body></html>";
		}
		else {
			if(controllerPosition.equals("EMAIL IS TAKEN")) {
				htmlRespone += "<html><body>";
				htmlRespone += "<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>";
		        htmlRespone += "<script>"
		        		+ "swal('Oopss..','Email is already taken', 'warning').then(()=> {window.location.href='/ERS/html/register.html'});"
		        		+ "</script>";
		        htmlRespone += "</body></html>";
			} else {
				htmlRespone += "<html><body>";
				htmlRespone += "<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>";
		        htmlRespone += "<script>"
		        		+ "swal('Oopss..','Something Went Wrong, Contact HR', 'error').then(()=> {window.location.href='/ERS/html/login.html'});"
		        		+ "</script>";
		        htmlRespone += "</body></html>";
			}
		}
		//pass your response back
		writer.println(htmlRespone);
	}
}
