package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursment;
import com.revature.service.EmployeeServiceImpl;
import com.revature.service.FinancialManagerServiceImpl;

public class FinancialManagerControllerImpl implements FinancialManagerController {

	//Singleton design
	private static FinancialManagerController financialManagerController;
	
	private FinancialManagerControllerImpl() {}
	
	public static FinancialManagerController getInstance() {
		if(financialManagerController == null) {
			financialManagerController = new FinancialManagerControllerImpl();
		}
		
		return financialManagerController;
	}
	
	//configure logger
	static final Logger logger = Logger.getLogger(FinancialManagerControllerImpl.class);

			
	@Override
	public int getEmployeeCount() {
		List<Employee> employeeList = FinancialManagerServiceImpl.getInstance().listEmployees();
		logger.info("GET EMPLOYEE COUNT");
		return employeeList.size();
	}

	@Override
	public List<Reimbursment> showReimbursements(HttpServletRequest request) {
		
		List<Reimbursment> reimbursmentList = FinancialManagerServiceImpl.getInstance().showReimbursements(request.getParameter("filter"));
		logger.info("LIST OF REIMBURSEMENETS IS VIEWED BY A FINANCE MANAGER");
		
		return reimbursmentList;
		
	}

	@Override
	public boolean modifyStatus(HttpServletRequest request) {
		boolean modifyResult = FinancialManagerServiceImpl.getInstance().modifyStatus(request.getParameter("selection"), request.getParameter("ticketId"));
		if(modifyResult == true) {
			logger.info("FINANCIAL MANAGER UPDATED REIMBURSEMENT: " + request.getParameter("ticketId") + " to: " + request.getParameter("selection"));
		} else {
			logger.debug("FINANCIAL MANAGER COULD NOT UPDATE REIMBURSEMENT: " + request.getParameter("ticketId") + " to: " + request.getParameter("selection"));
		}
		return modifyResult;
	
	}
	
}
