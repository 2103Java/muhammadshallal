package com.revature.request;


import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.controller.EmployeeControllerImpl;
import com.revature.controller.FinancialManagerControllerImpl;

public class RequestHelper {
	
	public RequestHelper() {}
		
	public static Object process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
			case "/ERS/auth":
				return EmployeeControllerImpl.getInstance().login(request);		
			case "/ERS/register":
				return EmployeeControllerImpl.getInstance().register(request);
			case "/ERS/get/employee/count":
				return EmployeeControllerImpl.getInstance().getEmployeeCount();
			case "/ERS/get/claim/count":
				return FinancialManagerControllerImpl.getInstance().getClaimCount();
			case "/ERS/logout":
				return EmployeeControllerImpl.getInstance().logout(request);
			case "/ERS/submitReimbursementServlet":
				return EmployeeControllerImpl.getInstance().submitReimbursement(request);
			case "/ERS/show/employee/reimbursments":
				return EmployeeControllerImpl.getInstance().showEmployeeReimbursements(request);
			case "/ERS/get/empclaim/count":
				return EmployeeControllerImpl.getInstance().showEmployeeReimbursements(request).size();
			case "/ERS/all/claim":
				return FinancialManagerControllerImpl.getInstance().showReimbursements(request);
			case "unregisterEmployee":
				return EmployeeControllerImpl.getInstance().unregister(request);
			case "/ERS/claim/status":
				return FinancialManagerControllerImpl.getInstance().getStatusById(request);
			case "/ERS/set/status":
				return FinancialManagerControllerImpl.getInstance().setStatusById(request);

			default:
				return new ClientMessage("not-implemented yes");
		}
	}
	
}