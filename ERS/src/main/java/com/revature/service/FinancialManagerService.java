package com.revature.service;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursment;

public interface FinancialManagerService {
	
	public List<Employee> listEmployees();
	public List<Reimbursment> showReimbursements(String filter1, String filter2);
	public boolean modifyStatus(String id, String newStatus);
	
}
