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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";
		
		htmlResposne += "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"ISO-8859-1\">\r\n"
				+ "		<title>Register a new account</title>\r\n"
				+ "		<script>  \r\n"
				+ "			function manage(txt) {\r\n"
				+ "		    	var pass1 = document.getElementById('pass1');\r\n"
				+ "		        var pass2 = document.getElementById('pass2');\r\n"
				+ "		        var bt = document.getElementById('register');\r\n"
				+ "		        if (pass1.value != pass2.value) {\r\n"
				+ "		            bt.disabled = true;\r\n"
				+ "		        }\r\n"
				+ "		        else {\r\n"
				+ "		            bt.disabled = false;\r\n"
				+ "		        }\r\n"
				+ "		    }    \r\n"
				+ "		</script>  \r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<form action=\"/ERS/RegisterServlet\" method=\"post\" name=\"registrationForm\">\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<label for=\"firstname\">Please enter your first name</label>\r\n"
				+ "			<input type=\"text\" name=\"firstname\" required/>\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<label for=\"lastname\">Please enter your last name</label>\r\n"
				+ "			<input type=\"text\" name=\"lastname\" required/>\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<label for=\"email\">Please enter a valid email</label>\r\n"
				+ "			<input type=\"email\" name=\"email\" required/>\r\n"
				+ "\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<label for=\"password\">Please enter a password</label>\r\n"
				+ "			<input type=\"password\" name=\"password\" id=\"pass1\" required/>\r\n"
				+ "\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<label for=\"confirmpassword\">Please confirm your password</label>\r\n"
				+ "			<input type=\"password\" name=\"confirmpassword\" id=\"pass2\" onkeyup=\"manage(this)\" required/>\r\n"
				+ "	\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<input type=\"submit\" id=\"register\" value=\"register\" disabled />\r\n"
				+ "			<button type = \"reset\" value = \"reset\" >reset</button>  \r\n"
				+ "			\r\n"
				+ "		</form>\r\n"
				+ "	</body>\r\n"
				+ "</html>";
		
		//pass your response back
		writer.println(htmlResposne);
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pass request to controller
		Object data = RequestHelper.process(request);
		
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlRespone = "";
		String controllerPosition = ((ClientMessage) data).getMessage();
		if (((ClientMessage) data).getMessage().equals("REGISTRATION SUCCESSFUL"))
			response.sendRedirect("html/login.html");
		else {
			if(controllerPosition.equals("EMAIL IS TAKEN")) {
				htmlRespone += "<html>";
		        htmlRespone += "<h2>This email is registered, please sign in.</h2>";
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
}
