package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Reimbursment;
import com.revature.util.ConnectionUtil;
	
public class ReimbursmentRepositoryJdbc implements ReimbursmentRepository {

	/*Singleton implementation*/
	private static ReimbursmentRepository reimbursmentRepository;
	
	private ReimbursmentRepositoryJdbc() {}
	
	public static ReimbursmentRepository getInstance() {
		if(reimbursmentRepository == null) {
			reimbursmentRepository = new ReimbursmentRepositoryJdbc();
		}
		
		return reimbursmentRepository;
	}

	@Override
	public List<Reimbursment> selectAllReimbursments() {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			String command = "SELECT * FROM reimbursments";
			PreparedStatement statement = connection.prepareStatement(command);
			
			ResultSet result = statement.executeQuery();

			List<Reimbursment> reimbursmentList = new ArrayList<>();
			while(result.next()) {
				Reimbursment reimbursment = new Reimbursment(result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("description"));
				reimbursment.setStatus(result.getString("status"));
				reimbursmentList.add(reimbursment);
			}

			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting all employees.");
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}
	
	@Override
	public List<Reimbursment> selectByEmployeeId(String employeeId) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			int statementIndex = 0;
			String command = "SELECT * FROM reimbursments WHERE employeeId=?";
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			statement.setString(++statementIndex, employeeId.toLowerCase());
			
			ResultSet result = statement.executeQuery();

			List<Reimbursment> reimbursmentList = new ArrayList<>();
			while(result.next()) {
				Reimbursment reimbursment = new Reimbursment(result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("description"));
				reimbursment.setStatus(result.getString("status"));
				reimbursmentList.add(reimbursment);
			}

			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting all employees.");
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}
    
	@Override
	public List<Reimbursment> selectByType(String type) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			int statementIndex = 0;
			String command = "SELECT * FROM reimbursments WHERE typeof=?";
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			statement.setString(++statementIndex, type);
			
			ResultSet result = statement.executeQuery();

			List<Reimbursment> reimbursmentList = new ArrayList<>();
			while(result.next()) {
				Reimbursment reimbursment = new Reimbursment(result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("description"));
				reimbursment.setStatus(result.getString("status"));
				reimbursmentList.add(reimbursment);
			}

			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting all employees.");
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}

	@Override
	public List<Reimbursment> selectByStatus(String status) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			int statementIndex = 0;
			String command = "SELECT * FROM reimbursments WHERE status=?";
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			statement.setString(++statementIndex, status);
			
			ResultSet result = statement.executeQuery();

			List<Reimbursment> reimbursmentList = new ArrayList<>();
			while(result.next()) {
				Reimbursment reimbursment = new Reimbursment(result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("description"));
				reimbursment.setStatus(result.getString("status"));
				reimbursmentList.add(reimbursment);
			}

			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting all employees.");
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}
	
	
	@Override
	public List<Reimbursment> selectByEmployeeType(String employeeId, String type) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			int statementIndex = 0;
			String command = "SELECT * FROM reimbursments WHERE employeeId=? AND typeof=?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, employeeId.toLowerCase());
			statement.setString(++statementIndex, type);
			ResultSet result = statement.executeQuery();

			List<Reimbursment> reimbursmentList = new ArrayList<>();
			while(result.next()) {
				Reimbursment reimbursment = new Reimbursment(result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("description"));
					reimbursment.setStatus(result.getString("status"));
					reimbursmentList.add(reimbursment);
				}

				return reimbursmentList;
				
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting all employees.");
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}

	@Override
	public List<Reimbursment> selectByEmployeeStatus(String employeeId, String status) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			int statementIndex = 0;
			String command = "SELECT * FROM reimbursments WHERE employeeId=? AND status=?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, employeeId.toLowerCase());
			statement.setString(++statementIndex, status);
			ResultSet result = statement.executeQuery();

			List<Reimbursment> reimbursmentList = new ArrayList<>();
			while(result.next()) {
				Reimbursment reimbursment = new Reimbursment(result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("description"));
					reimbursment.setStatus(result.getString("status"));
					reimbursmentList.add(reimbursment);
				}

				return reimbursmentList;
				
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with selecting all employees.");
			e.printStackTrace();
		} 
		return new ArrayList<>();
	}

	@Override
	public boolean insert(Reimbursment reimbursment) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			int statementIndex = 0;
			String command = "INSERT INTO reimbursments VALUES(?,?,?,?,?,?,?)";
			
			PreparedStatement statement;
			statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, reimbursment.getId());
			statement.setString(++statementIndex, reimbursment.getEmployeeId());
			statement.setDouble(++statementIndex, reimbursment.getAmount());
			statement.setString(++statementIndex, reimbursment.getType());
			statement.setString(++statementIndex, reimbursment.getDescription());
			statement.setString(++statementIndex, reimbursment.getStatus());
			statement.setObject(++statementIndex, reimbursment.getSubmissionDate());
			
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with inserting a reimbursment.");
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean delete(String reimbursmentId) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			
			int statementIndex = 0;
			String command = "DELETE FROM reimbursments WHERE id=?";
			
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			statement.setString(++statementIndex, reimbursmentId);
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with deleting a reimbursment.");
			e.printStackTrace();
		}

		return false;
	}

	
	
}