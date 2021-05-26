package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursment;
import com.revature.service.FinancialManagerServiceImpl;

public class FinancialManagerControllerImpl implements FinancialManagerController {

	//Singleton design
	private static FinancialManagerController financialManagerController;
	
	private FinancialManagerControllerImpl() {}
	private static List<String> financeManagers = new ArrayList<String>();
	
	public static FinancialManagerController getInstance() {
		if(financialManagerController == null) {
			financeManagers.add("hshallal@gmail.com");
			financeManagers.add("abdul.moeed.ak@gmail.com");
			financialManagerController = new FinancialManagerControllerImpl();
		}
		
		return financialManagerController;
	}
	
	//configure logger
	static final Logger logger = Logger.getLogger(FinancialManagerControllerImpl.class);

			
	@Override
	public int getEmployeeCount() {
		List<Employee> employeeList = FinancialManagerServiceImpl.getInstance().listEmployees();
		logger.info("GET EMPLOYEE COUNT BY A FINANCE MANAGER");
		return employeeList.size();
	}

	@Override
	public int getClaimCount() {
		logger.info("GET CLAIM COUNT BY A FINANCE MANAGER");
		return FinancialManagerServiceImpl.getInstance().getClaimCount();
	}

	@Override
	public String getStatusById(HttpServletRequest request) {
		logger.info("GET STATUS OF A CLAIM BY ID BY A FINANCE MANAGER");
		return FinancialManagerServiceImpl.getInstance().getStatusById(request.getParameter("id"));
	}

	@Override
	public boolean setStatusById(HttpServletRequest request) {
		logger.info("SET STATUS OF A CLAIM BY ID BY A FINANCE MANAGER");
		return FinancialManagerServiceImpl.getInstance().setStatusById(request.getParameter("id"), request.getParameter("status"));
	}
	
	@Override
	public List<Reimbursment> showReimbursements(HttpServletRequest request) {
		List<Reimbursment> reimbursmentList = null;
		if(request.getParameter("filter1") == null && request.getParameter("filter2") == null)
			reimbursmentList = FinancialManagerServiceImpl.getInstance().showReimbursements("all", "all");
		else reimbursmentList = FinancialManagerServiceImpl.getInstance().showReimbursements(request.getParameter("filter1"), request.getParameter("filter2"));
		logger.info("LIST OF REIMBURSEMENETS IS VIEWED BY A FINANCE MANAGER");
		return reimbursmentList;
		
	}
	
}
