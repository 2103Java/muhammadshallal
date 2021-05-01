package com.revature.service;

import java.util.List;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;

public interface FinancialManagerService {
	
	public int getClaimCount();
	public String getStatusById(String id);
	public boolean setStatusById(String id, String status);
	public List<Employee> listEmployees();
	public List<Reimbursment> showReimbursements(String filter1, String filter2);
}
