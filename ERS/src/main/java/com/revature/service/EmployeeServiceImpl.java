package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;
import com.revature.repository.EmployeeRepositoryJdbc;
import com.revature.repository.ReimbursmentRepositoryJdbc;

public class EmployeeServiceImpl implements EmployeeService {
	
	//Singleton design
	private static EmployeeService employeeService;
	
	private EmployeeServiceImpl() {}
	
	public static EmployeeService getInstance() {
		if(employeeService == null) {
			employeeService = new EmployeeServiceImpl();
		}
		
		return employeeService;
	}

	//configure logger
	static final Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
			
	//Implementations
	@Override
	public boolean employeeExists(String username) {
		logger.info("CHECK EMPLOYEE EXISTS SERVICE TO " + username);
		return EmployeeRepositoryJdbc.getInstance().selectById(username);
	}
	
	@Override
	public boolean registerEmployee(Employee employee) {
		logger.info("REGISTRATION SERVICE TO " + employee.getEmail());
		return EmployeeRepositoryJdbc.getInstance().insert(employee);
	}

	@Override
	public boolean unregisterEmployee(String username) {
		logger.info("UNREGISTRATION SERVICE TO " + username);
		return EmployeeRepositoryJdbc.getInstance().delete(username);
	}


	@Override
	public int getEmployeeCount() {
		logger.info("LIST ALL EMPLOYEES SERVICE");
		return EmployeeRepositoryJdbc.getInstance().selectAllEmployees().size();
	}
	
	@Override
	public Employee login(String username, String password) {
		logger.info("LOGIN SERVICE TO " + username);
		return EmployeeRepositoryJdbc.getInstance().authenticate(username, password);
	}

	
	@Override
	public boolean submitReimbursment(Reimbursment reimbursment) {
		logger.info("REIMBURSEMENET SUBMISSION SERVICE TO " + reimbursment.getEmployeeId());
		return ReimbursmentRepositoryJdbc.getInstance().insert(reimbursment);
	}
	
	@Override
	public List<Reimbursment> showMyPreviousReimbursments(String username, String filter1, String filter2) {
		logger.info("SHOW PREVIOUS AND " + filter1.toUpperCase() + " AND " + filter2.toUpperCase() + " REIMBURSEMENETS SERVICE TO " + username);
		List<Reimbursment> reimbursments = new ArrayList<>();
		if(filter1.equals("all") && filter2.equals("all"))
			reimbursments = ReimbursmentRepositoryJdbc.getInstance().selectByEmployeeId(username);
		else reimbursments = ReimbursmentRepositoryJdbc.getInstance().selectByEmployeeTypeAndStatus(username, filter1, filter2);
		return reimbursments;
	}
}

