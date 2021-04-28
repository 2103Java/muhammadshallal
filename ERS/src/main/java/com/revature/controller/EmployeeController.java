package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Employee;
import com.revature.model.Reimbursment;

public interface EmployeeController {
	/**
	 * Registers an employee
	 * We first establish controller-model connections
	 * This should be modified latter to receive a servlet
	 */
	
	public ClientMessage unregister(String email);
	
	public boolean isRegistered(String email);
	
	public ClientMessage logout(String email, String password);
	
	public Employee login(HttpServletRequest request);
	public ClientMessage register(HttpServletRequest request);
	
	public ClientMessage submitReimbursement(HttpServletRequest request);
	public List<Reimbursment> showEmployeeReimbursements(HttpServletRequest request);
	
}