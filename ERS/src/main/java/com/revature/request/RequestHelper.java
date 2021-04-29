package com.revature.request;


import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.controller.EmployeeControllerImpl;
import com.revature.controller.FinancialManagerController;
import com.revature.controller.FinancialManagerControllerImpl;

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
		FinancialManagerController financialManagerController = new FinancialManagerControllerImpl();
		switch(request.getRequestURI()) {
			case "/ERS/auth":
				return EmployeeControllerImpl.getInstance().login(request);		
			case "/ERS/register":
				return EmployeeControllerImpl.getInstance().register(request);
			case "/ERS/get/employee/count":
				return EmployeeControllerImpl.getInstance().getEmployeeCount();
			case "/ERS/get/claim/count":
				return financialManagerController.getClaimCount();
			case "/ERS/submitReimbursementServlet":
				return EmployeeControllerImpl.getInstance().submitReimbursement(request);
			case "/ERS/showEmployeeReimbursmentsServlet":
				return EmployeeControllerImpl.getInstance().showEmployeeReimbursements(request);
			

			default:
				return new ClientMessage("not-implemented yes");
		}
	}
	
}