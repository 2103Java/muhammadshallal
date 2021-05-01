package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;

public interface EmployeeController {
	
	public boolean isRegistered(String email);
	
	public Employee login(HttpServletRequest request);
	public ClientMessage logout(HttpServletRequest request);
	public ClientMessage register(HttpServletRequest request);
	//public ClientMessage unregister(HttpServletRequest request);
	
	public ClientMessage submitReimbursement(HttpServletRequest request);
	public List<Reimbursment> showEmployeeReimbursements(HttpServletRequest request);
	
	
}