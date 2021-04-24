package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeRepositoryJdbc implements EmployeeRepository {

	//Singleton design
	private static EmployeeRepository employeeRepository;
	
	private EmployeeRepositoryJdbc() {}
	
	public static EmployeeRepository getInstance() {
		if(employeeRepository == null) {
			employeeRepository = new EmployeeRepositoryJdbc();
		}
		
		return employeeRepository;
	}
	
	//Implementations
	@Override
	public boolean selectById(String username) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			
			int statementIndex = 0;
			String command = "SELECT * FROM employees WHERE username=?";

			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			statement.setString(++statementIndex, username.toLowerCase());
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting an employee.");
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public List<Employee> selectAllEmployees() {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			String command = "SELECT * FROM employees";
			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();

			List<Employee> employeeList = new ArrayList<>();
			while(result.next()) {
				employeeList.add(new Employee(result.getString("firstname"), 
											  result.getString("lastname"),
											  result.getString("username"),
			            					  "hashed_password"));
			}

			return employeeList;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting all employees.");
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}

	@Override
	public boolean insert(Employee employee) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			int statementIndex = 0;
			String command = "INSERT INTO employees VALUES(?,?,?)";
			
			PreparedStatement statement;
			statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, employee.getFirstName().toUpperCase());
			statement.setString(++statementIndex, employee.getLastName().toUpperCase());
			statement.setString(++statementIndex, employee.getEmail().toLowerCase());
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with inserting an employee.");
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean delete(String username) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			
			int statementIndex = 0;
			String command = "DELETE FROM employees WHERE username=?";
			
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			statement.setString(++statementIndex, username.toLowerCase());
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with deleting an employee.");
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// password is supposed to be hashed
		// logic is to select using username and hashed password
		return false;
	}

	//This method may be unnecessary since we logout upstream at the controller level
	//by invoking request.getSession().invalidate();
	@Override
	public boolean deauthenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}