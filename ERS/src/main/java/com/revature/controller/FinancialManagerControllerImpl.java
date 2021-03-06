package com.revature.controller;

import com.revature.repository.ReimbursmentRepositoryJdbc;
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
		List<Reimbursment> reimbursmentList = null;
		if(request.getParameter("filter1") == null && request.getParameter("filter2") == null)
			reimbursmentList = FinancialManagerServiceImpl.getInstance().showReimbursements("all", "all");
		else reimbursmentList = FinancialManagerServiceImpl.getInstance().showReimbursements(request.getParameter("filter1"), request.getParameter("filter2"));
		logger.info("LIST OF REIMBURSEMENETS IS VIEWED BY A FINANCE MANAGER");
		return reimbursmentList;
		
	}

	
	@Override
	public int getClaimCount() {
		// TODO Auto-generated method stub
		return ReimbursmentRepositoryJdbc.getInstance().getClaim().size();
	}

	@Override
	public String getStatusById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return ReimbursmentRepositoryJdbc.getInstance().getStatusById(request.getParameter("id"));
	}

	@Override
	public boolean setStatusById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return ReimbursmentRepositoryJdbc.getInstance().setStatusById(request.getParameter("id"), request.getParameter("status"));
	}
	
}
