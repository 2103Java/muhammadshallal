package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeRepository {
	
	//DQL
	public boolean selectById(String username);
	public List<Employee> selectAllEmployees();
		
	//DML
	public boolean insert(Employee employee);
	public boolean delete(String username);
	public Employee authenticate(String username, String password);

}