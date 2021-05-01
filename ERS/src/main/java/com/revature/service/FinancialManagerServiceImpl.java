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
	public List<Reimbursment> showReimbursements(String filter1, String filter2) {
		logger.info("SHOW " + filter1.toUpperCase() + " REIMBURSEMENETS SERVICE TO A FINANCE MANAGER");
		if(filter1.equals("all") && filter2.equals("all")) {
			return ReimbursmentRepositoryJdbc.getInstance().selectAllReimbursments();
		} 
		else return ReimbursmentRepositoryJdbc.getInstance().selectByTypeAndStatus(filter1, filter2);
	}

}
