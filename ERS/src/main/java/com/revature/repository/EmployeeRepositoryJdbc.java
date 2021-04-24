package com.revature.repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

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
	
	//Hashing password
	protected String hashPass(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = new byte[16];
		KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = f.generateSecret(spec).getEncoded();
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(hash);
	}
	
	//Implementations
	@Override
	public boolean selectById(String username) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			
			int statementIndex = 0;
			String command = "SELECT * FROM employees WHERE email=?";

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
					
			int statementIndex = 0;
			String command = "SELECT * FROM employees WHERE isManager=?";
			
			PreparedStatement statement = connection.prepareStatement(command);
			
			//Set attributes to be inserted
			statement.setBoolean(++statementIndex, false);
			
			ResultSet result = statement.executeQuery();

			List<Employee> employeeList = new ArrayList<>();
			while(result.next()) {
				employeeList.add(new Employee(result.getString("firstname"), 
											  result.getString("lastname"),
											  result.getString("username"),
											  result.getString("password"),
			            					  result.getBoolean("isManager")));
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
			
			String hashPass = hashPass(employee.getPassword());
			//System.out.println("hashPass:" + hashPass);
			
			int statementIndex = 0;
			String command = "INSERT INTO employees VALUES(?,?,?,?,?)";
			
			PreparedStatement statement;
			statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, employee.getFirstName().toUpperCase());
			statement.setString(++statementIndex, employee.getLastName().toUpperCase());
			statement.setString(++statementIndex, employee.getEmail().toLowerCase());
			statement.setString(++statementIndex, hashPass);
			statement.setBoolean(++statementIndex, employee.getIsManager());
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			System.out.println("Issues with registering an employee.");
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
	public Employee authenticate(String username, String password) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			String hashPass = hashPass(password);
			
			int statementIndex = 0;
			String command = "SELECT * FROM employees WHERE email=? AND password=?";
			
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			statement.setString(++statementIndex, username.toLowerCase());
			statement.setString(++statementIndex, hashPass);
			
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return new Employee(result.getString("firstname"), 
						            result.getString("lastname"),
						            result.getString("email"), 
						            result.getString("password"), 
						            result.getBoolean("isManager"));
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			return null;
		}
	}

	//This method may be unnecessary since we logout upstream at the controller level
	//by invoking request.getSession().invalidate();
	@Override
	public boolean deauthenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}