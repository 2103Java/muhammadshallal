package com.revature.repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;

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
	
	//configure logger
	static final Logger logger = Logger.getLogger(EmployeeRepositoryJdbc.class);
			
	//Method for Hashing password
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
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", IS FOUND IN RDS");
				return true;
			} else {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", IS NOT FOUND IN RDS");
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES SELECTING AN EMPLOYEE WITH USERNAME: " + username);
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<Employee> selectAllEmployees() {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			String command = "SELECT * FROM employees WHERE isManager=?";
			
			PreparedStatement statement = connection.prepareStatement(command);
			
			//Set attributes to be inserted
			statement.setBoolean(1, false);
			
			ResultSet result = statement.executeQuery();

			List<Employee> employeeList = new ArrayList<>();
			while(result.next()) {
				employeeList.add(new Employee(result.getString("firstname"), 
											  result.getString("lastname"),
											  result.getString("email"),
											  result.getString("password"),
			            					  result.getBoolean("isManager")));
			}
			logger.info("ALL EMPLOYEES ARE SELECTED");
			return employeeList;
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES WITH SELECTING ALL EMPLOYEES");
			e.printStackTrace();
			return null;
		} 
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
				logger.info("AN EMPLOYEE IS INSERTED TO THE RDS");
				return true;
			} else {
				logger.debug("ISSUES WITH INSERTING AN EMPLOYEE NON EXCEPTION");
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			logger.debug("ISSUES WITH INSERTING AN EMPLOYEE");
			e.printStackTrace();
			return false;
		}
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
				logger.info("AN EMPLOYEE IS DELETED FROM THE RDS");
				return true;
			} else {
				logger.debug("ISSUES WITH DELETING AN EMPLOYEE NON EXCEPTION");
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES WITH DELETING AN EMPLOYEE");
			e.printStackTrace();
			return false;
		}
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
				logger.info("AN EMPLOYEE IS AUTHENTICATED");
				return new Employee(result.getString("firstname"), 
						            result.getString("lastname"),
						            result.getString("email"), 
						            result.getString("password"), 
						            result.getBoolean("isManager"));
				
			} else {
				logger.debug("AN UNREGISTERED EMPLOYEE TRIED TO AUTHENTICATE");
				return null;
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			logger.debug("ISSUES WITH AN EMPLOYEE BEING AUTHENTICATED");
			return null;
		}
	}
}