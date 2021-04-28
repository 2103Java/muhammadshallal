package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.controller.EmployeeControllerImpl;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;
import com.revature.repository.EmployeeRepository;
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
	public boolean employeeExists(String username) {
		logger.info("CHECK EMPLOYEE EXISTS SERVICE TO " + username);
		return EmployeeRepositoryJdbc.getInstance().selectById(username);
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
	public boolean logout(String username, String password) {
		logger.info("LOGOUT SERVICE TO " + username);
		return EmployeeRepositoryJdbc.getInstance().deauthenticate(username, password);
	}

	
	@Override
	public boolean submitReimbursment(Reimbursment reimbursment) {
		logger.info("REIMBURSEMENET SUBMISSION SERVICE TO " + reimbursment.getEmployeeId());
		return ReimbursmentRepositoryJdbc.getInstance().insert(reimbursment);
	}
	
	@Override
	public List<Reimbursment> showMyPreviousReimbursments(String username, String filter) {
		logger.info("SHOW PREVIOUS AND " + filter.toUpperCase() + " REIMBURSEMENETS SERVICE TO " + username);
		if(filter.equals("all")) {
			return ReimbursmentRepositoryJdbc.getInstance().selectByEmployeeId(username);
		} else if(filter.equals("pending") || filter.equals("approved") || filter.equals("denied")){
			return ReimbursmentRepositoryJdbc.getInstance().selectByEmployeeStatus(username, filter);
		} else {
			return ReimbursmentRepositoryJdbc.getInstance().selectByEmployeeType(username, filter);
		}
	}
}

