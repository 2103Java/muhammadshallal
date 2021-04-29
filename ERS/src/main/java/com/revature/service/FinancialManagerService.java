package com.revature.service;

import java.util.List;

import com.revature.ajax.ClientMessage;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;

public interface FinancialManagerService {

	public List<Employee> listEmployees();
	public ClientMessage viewAllEmployees();
	public List<Reimbursment> showReimbursements(String filter);
}
