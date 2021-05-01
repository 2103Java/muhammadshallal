package com.revature.service;

import java.util.List;

import com.revature.model.*;

public interface EmployeeService {
	
	public boolean registerEmployee(Employee employee);
	public boolean unregisterEmployee(String username);
	
	public boolean employeeExists(String username);
	public int getEmployeeCount();
	
	
	public Employee login(String username, String password);
	
	public boolean submitReimbursment(Reimbursment reimbursment);
	public List<Reimbursment> showMyPreviousReimbursments(String username, String type, String status);
}