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
	
	public boolean authenticate(String username, String password); //NIY
	public boolean deauthenticate(String username, String password); //NIY
	
	
	
	// Do we want to include updates to the reimbursement requests?
//	public boolean updateUsername(String oldUsername, String newUsername); 
//	public boolean updateFirstName(String username, String newFirstName); 
//	public boolean updateLastName(String username, String newLastName); 
//	public boolean updatePassword(String username, String newPassword); 

}