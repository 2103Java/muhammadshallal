package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public int getClaimCount() {
		logger.info("GET CLAIM COUNT BY A FINANCE MANAGER");
		return ReimbursmentRepositoryJdbc.getInstance().getClaim().size();
	}
	
	@Override
	public String getStatusById(String id) {
		logger.info("GET STATUS OF A CLAIM BY ID BY A FINANCE MANAGER");
		return ReimbursmentRepositoryJdbc.getInstance().getStatusById(id);
	}

	@Override
	public boolean setStatusById(String id, String status) {
		logger.info("SET STATUS OF A CLAIM BY ID BY A FINANCE MANAGER");
		return ReimbursmentRepositoryJdbc.getInstance().setStatusById(id, status);
	}
	
	@Override
	public List<Employee> listEmployees() {
		logger.info("LIST ALL EMPLOYEES SERVICE BY A FINANCE MANAGER");
		return EmployeeRepositoryJdbc.getInstance().selectAllEmployees();
	}
	
	@Override
	public List<Reimbursment> showReimbursements(String filter1, String filter2) {
		logger.info("SHOW " + filter1.toUpperCase() + " " + filter2.toUpperCase() + " REIMBURSEMENETS SERVICE TO A FINANCE MANAGER");
		List<Reimbursment> reimbursments = new ArrayList<>();
		
		if(filter1.equals("all") && filter2.equals("all")) {
			reimbursments =  ReimbursmentRepositoryJdbc.getInstance().selectAllReimbursments();
		} 
		else reimbursments =  ReimbursmentRepositoryJdbc.getInstance().selectByTypeAndStatus(filter1, filter2);
		return reimbursments;
	}

}
