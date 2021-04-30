package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;
import com.revature.repository.EmployeeRepositoryJdbc;
import com.revature.repository.ReimbursmentRepositoryJdbc;

public class FinancialManagerServiceImpl implements FinancialManagerService {
	
	//Singleton design
	private static FinancialManagerService financialManagerService;
	
	private FinancialManagerServiceImpl() {}
	
	public static FinancialManagerService getInstance() {
		if(financialManagerService == null) {
			financialManagerService = new FinancialManagerServiceImpl();
		}
		
		return financialManagerService;
	}

	//configure logger
	static final Logger logger = Logger.getLogger(FinancialManagerServiceImpl.class);
		
	
	@Override
	public List<Employee> listEmployees() {
		logger.info("LIST ALL EMPLOYEES SERVICE");
		return EmployeeRepositoryJdbc.getInstance().selectAllEmployees();
	}
	
	@Override
	public List<Reimbursment> showReimbursements(String filter) {
		logger.info("SHOW " + filter.toUpperCase() + " REIMBURSEMENETS SERVICE TO A FINANCE MANAGER");
		if(filter.equals("all")) {
			return ReimbursmentRepositoryJdbc.getInstance().selectAllReimbursments();
		} else if(filter.equals("pending") || filter.equals("approved") || filter.equals("denied")){
			return ReimbursmentRepositoryJdbc.getInstance().selectByStatus(filter);
		} else {
			return ReimbursmentRepositoryJdbc.getInstance().selectByType(filter);
		}
	}

	@Override
	public boolean modifyStatus(String id, String newStatus) {
		boolean updateResult = ReimbursmentRepositoryJdbc.getInstance().updateStatus(id, newStatus);
		if(updateResult == true) {
			logger.info("FINANCIAL MANAGER UPDATED REIMBURSEMENT: " + id + " to: " + newStatus);
		} else {
			logger.debug("FINANCIAL MANAGER COULD NOT UPDATE REIMBURSEMENT: " + id + " to: " + newStatus);
		}
		return updateResult;
	}
}
