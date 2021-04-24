package com.revature.controller;

import com.revature.service.EmployeeService;
import com.revature.service.EmployeeServiceImpl;

public class EmployeeControllerImpl implements EmployeeController{

	private EmployeeService employeeService = new EmployeeServiceImpl();
	
	@Override
	public int getEmployeeCount() {
		// TODO Auto-generated method stub
		return employeeService.getEmployeeCount();
	}

	
	
}
