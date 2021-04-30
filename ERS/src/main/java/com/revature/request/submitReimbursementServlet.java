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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get response writer
		PrintWriter writer = response.getWriter();
		String htmlResposne = "";
		
		//Add your html
		htmlResposne += "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"ISO-8859-1\">\r\n"
				+ "		<title>Submit a reimbursement request</title>\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<form action=\"/ERS/submitReimbursementServlet\" method=\"post\" name=\"submissionForm\" enctype = \"multipart/form-data\">\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<label for=\"Amount\">Reimbursement Amount in $</label>\r\n"
				+ "			<input type=\"number\" step=\"0.01\" name=\"Amount\" required/>\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<p>Specify one category:</p>\r\n"
				+ "			<label for=\"Travel\">Travel</label>\r\n"
				+ "			<input type=\"radio\" name=\"category\" value=\"Travel\">\r\n"
				+ "			<label for=\"Lodging\">Lodging</label>\r\n"
				+ "			<input type=\"radio\" name=\"category\" value=\"Lodging\">\r\n"
				+ "			<label for=\"Food\">Food</label>\r\n"
				+ "			<input type=\"radio\" name=\"category\" value=\"Food\">\r\n"
				+ "			<label for=\"Other\">Other</label>\r\n"
				+ "			<input type=\"radio\" name=\"category\" value=\"Other\">\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<p>Please provide a brief description of why this is a business expense.</p>\r\n"
				+ "			<textarea rows=\"5\" cols=\"50\" name=\"Description\" id=\"Description\"></textarea>\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<label for=\"fileselect\">Upload a receipt image</label>\r\n"
				+ "			<input type=\"file\" name=\"fileselect\" required/>\r\n"
				+ "			\r\n"
				+ "			<br>\r\n"
				+ "			<br>\r\n"
				+ "			<input type=\"submit\" value=\"Submit\">\r\n"
				+ "		</form>\r\n"
				+ "		<br>\r\n"
				+ "		<br>\r\n"
				+ "     <a href=\"/ERS/LogoutServlet\">Logout</a>\r\n"
				+ "	</body>\r\n"
				+ "	</html>";
		
		//pass your response back
		writer.println(htmlResposne);	
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		
		//Pass request to controller
		Object data = RequestHelper.process(request);
				
		if(((ClientMessage) data).getMessage().equals("REIMBURSEMENET SUBMISSION SUCCESSFUL")) {
			
			httpSession.setAttribute("curMessage", "Your reimbursement request was successfully submitted. It is pending approval by a finance manager.");
	        response.sendRedirect("/ERS/EmployeeServlet");
	        
		} else if (((ClientMessage) data).getMessage().equals("REIMBURSEMENET SUBMISSION UNSUCCESSFUL")){
			httpSession.setAttribute("curMessage", "Your reimbursement request was unsuccessfully submitted.");
	        response.sendRedirect("/ERS/EmployeeServlet");
		}
		
	}

}