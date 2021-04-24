package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Reimbursment;

public interface EmployeeController {
	/**
	 * Registers an employee
	 * We first establish controller-model connections
	 * This should be modified latter to receive a servlet
	 */
	public ClientMessage register(String firstname, String lastname, String username);
	public ClientMessage unregister(String username);
	
	public ClientMessage isRegistered(String username);
	public ClientMessage viewAllEmployees();
	
	public ClientMessage login(String username, String password);
	public ClientMessage logout(String username, String password);
	
	public ClientMessage submitReimbursement(HttpServletRequest request);
	public List<Reimbursment> showEmployeeReimbursements(HttpServletRequest request);
	public int getEmployeeCount(HttpServletRequest request);
	
}