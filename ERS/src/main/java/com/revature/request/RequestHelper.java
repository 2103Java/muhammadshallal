package com.revature.request;


import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.controller.EmployeeControllerImpl;

public class RequestHelper {
	
	public RequestHelper() {}
	
	//This RequestHelper is functioning based on controller-model connections, no views yet
	// process is not really supposed to have more than an httpservlet request parameter
	// which is passed to the respective controller with a specific endpoitn
	// Here, for the purpose of testing controller-model, we input other individual parameters
	// These parameters are supposed to be acquired from a request usign getParameter method
	// whatToDo is equivalent to request.getRequestURI()
	// request.getRequestURI() is equivalent to /signup, etc
	//Double amount, String type, String description
	public static ClientMessage process(String whatToDo, String firstname, String lastname, String username, String password) {
		switch(whatToDo) {
			case "registerNewEmployee":
				return EmployeeControllerImpl.getInstance().register(firstname, lastname, username);
			case "unregisterEmployee":
				return EmployeeControllerImpl.getInstance().unregister(username);
			case "isRegistered":
				return EmployeeControllerImpl.getInstance().isRegistered(username);
			case "viewAllEmployees":
				return EmployeeControllerImpl.getInstance().viewAllEmployees();
			case "login":
				return EmployeeControllerImpl.getInstance().login(username, password);
			case "logout":
				return EmployeeControllerImpl.getInstance().logout(username, password);
			
			default:
				return new ClientMessage("not-implemented yes");
		}
	}
	
	public static Object process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
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