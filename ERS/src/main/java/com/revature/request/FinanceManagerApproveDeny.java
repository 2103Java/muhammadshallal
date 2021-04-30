package com.revature.request;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ERS/FinanceManagerApproveDeny")
public class FinanceManagerApproveDeny extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FinanceManagerApproveDeny() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Pass request to controller
		Object fromController =  RequestHelper.process(request);
		System.out.println("Original filter from doPost in FinanceManagerApproveDeny" + request.getParameter("filter"));
	}

}
