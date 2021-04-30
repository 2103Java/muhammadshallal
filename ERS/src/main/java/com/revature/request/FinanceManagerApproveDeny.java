package com.revature.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ERS/FinanceManagerApproveDeny")
public class FinanceManagerApproveDeny extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FinanceManagerApproveDeny() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pass request to controller
		Object fromController =  RequestHelper.process(request);
		
		//Now, we need to refresh the table that was shown after the modifications took place
		HttpSession httpSession = request.getSession(false);		
		response.sendRedirect("/ERS/showReimbursmentsServlet");
	}

}
