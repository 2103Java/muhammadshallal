package com.revature.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ajax.ClientMessage;

@WebServlet("/ERS/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pass request to controller
		Object data = RequestHelper.process(request);
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlRespone = "";
		String controllerPosition = ((ClientMessage) data).getMessage();
		if(controllerPosition.equals("EMAIL IS TAKEN")) {
			htmlRespone += "<html>";
	        htmlRespone += "<h2>This email is registered, please sign in.</h2>";    
	        htmlRespone += "<a href=\"index.html\">Sign in</a>";
	        htmlRespone += "</html>";
		} else if (((ClientMessage) data).getMessage().equals("REGISTRATION SUCCESSFUL")) {
			htmlRespone += "<html>";
	        htmlRespone += "<h2>You are successfully registered.</h2>";    
	        htmlRespone += "<a href=\"index.html\">Sign in</a>";
	        htmlRespone += "</html>";
		} else {
			htmlRespone += "<html>";
	        htmlRespone += "<h2>You registration was not successful, please contact human resources.</h2>";    
	        htmlRespone += "<a href=\"index.html\">Sign in</a>";
	        htmlRespone += "</html>";
		}
		
		//pass your response back
		writer.println(htmlRespone);
	}
}
