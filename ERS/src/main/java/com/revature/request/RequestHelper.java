package com.revature.request;


import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.controller.EmployeeControllerImpl;

public class RequestHelper {
	
	public RequestHelper() {}
	
	public static ClientMessage process(String whatToDo, String firstname, String lastname, String username, String password, boolean isManager) {
		switch(whatToDo) {
			case "unregisterEmployee":
				return EmployeeControllerImpl.getInstance().unregister(username);
			case "viewAllEmployees":
				return EmployeeControllerImpl.getInstance().viewAllEmployees();
			case "logout":
				return EmployeeControllerImpl.getInstance().logout(username, password);
			
			default:
				return new ClientMessage("not-implemented yes");
		}
	}
	
	public static Object process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
			case "/ERS/AuthServlet":
				return EmployeeControllerImpl.getInstance().login(request);		
			case "/ERS/RegisterServlet":
				return EmployeeControllerImpl.getInstance().register(request);
			case "/ERS/EmployeeCountServlet":
				return EmployeeControllerImpl.getInstance().getEmployeeCount(request);
			case "/ERS/submitReimbursementServlet":
				return EmployeeControllerImpl.getInstance().submitReimbursement(request);
			case "/ERS/showEmployeeReimbursmentsServlet":
				return EmployeeControllerImpl.getInstance().showEmployeeReimbursements(request);
			

			default:
				return new ClientMessage("not-implemented yes");
		}
	}
	
}