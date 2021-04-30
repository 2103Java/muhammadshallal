package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

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
	
	//configure logger
	static final Logger logger = Logger.getLogger(ReimbursmentRepositoryJdbc.class);

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
				Reimbursment reimbursment = new Reimbursment(
						result.getString("id"),
						result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("status"),
						  result.getDate("submissiondate").toLocalDate(),
						  result.getString("description"));
				reimbursmentList.add(reimbursment);
			}
			logger.info("ALL REIMBURSEMENTS ARE SELECTED");
			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES WITH SELECTING ALL REIMBURSEMENTS");
			e.printStackTrace();
			return new ArrayList<>();
		} 
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
				Reimbursment reimbursment = new Reimbursment(
						result.getString("id"),
						result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("status"),
						  result.getDate("submissiondate").toLocalDate(),
						  result.getString("description"));
				reimbursmentList.add(reimbursment);
			}
			logger.info("REIMBURSEMENTS OF EMPLOYEE WITH THIS USERNAME: " + employeeId.toUpperCase() + ", WERE FOUND IN RDS");
			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			logger.info("ISSUES WITH SELECTING REIMBURSEMENTS OF EMPLOYEE WITH THIS USERNAME: " + employeeId.toUpperCase() + ", IN RDS");
			e.printStackTrace();
			return new ArrayList<>();
		} 
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
				Reimbursment reimbursment = new Reimbursment(
						result.getString("id"),
						result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("status"),
						  result.getDate("submissiondate").toLocalDate(),
						  result.getString("description"));
				reimbursmentList.add(reimbursment);
			}
			logger.info("REIMBURSEMENTS OF TYPE: " + type.toUpperCase() + ", WERE FOUND IN RDS");

			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			logger.info("ISSUES WITH SELECTING REIMBURSEMENTS OF TYPE: " + type.toUpperCase() + ", IN RDS");
			e.printStackTrace();
			return new ArrayList<>();
		} 
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
				Reimbursment reimbursment = new Reimbursment(
						result.getString("id"),
						result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("status"),
						  result.getDate("submissiondate").toLocalDate(),
						  result.getString("description"));
				reimbursmentList.add(reimbursment);
			}
			logger.info("REIMBURSEMENTS OF STATUS: " + status.toUpperCase() + ", WERE FOUND IN RDS");
			return reimbursmentList;
		} catch (ClassNotFoundException | SQLException e) {
			logger.info("ISSUES WITH SELECTING REIMBURSEMENTS OF STATUS: " + status.toUpperCase() + ", IN RDS");
			e.printStackTrace();
			return new ArrayList<>();
		} 
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
				Reimbursment reimbursment = new Reimbursment(
						result.getString("id"),
						result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("status"),
						  result.getDate("submissiondate").toLocalDate(),
						  result.getString("description"));
				reimbursmentList.add(reimbursment);
				}
			logger.info("REIMBURSEMENTS OF TYPE: " + type.toUpperCase() + " FOR EMPLOYEE WITH USERNAME: " + employeeId + ", WERE FOUND IN RDS");
			return reimbursmentList;
				
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES FINDING REIMBURSEMENTS OF TYPE: " + type.toUpperCase() + " FOR EMPLOYEE WITH USERNAME: " + employeeId + ", IN RDS");
			e.printStackTrace();
			return new ArrayList<>();
		} 
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
				Reimbursment reimbursment = new Reimbursment(
						result.getString("id"),
						result.getString("employeeId"), 
						  result.getDouble("amount"),
						  result.getString("typeof"),
						  result.getString("status"),
						  result.getDate("submissiondate").toLocalDate(),
						  result.getString("description"));
				reimbursmentList.add(reimbursment);
				}
			logger.info("REIMBURSEMENTS OF STATUS: " + status.toUpperCase() + " FOR EMPLOYEE WITH USERNAME: " + employeeId + ", WERE FOUND IN RDS");
			return reimbursmentList;
				
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES FINDING REIMBURSEMENTS OF STATUS: " + status.toUpperCase() + " FOR EMPLOYEE WITH USERNAME: " + employeeId + ", IN RDS");
			e.printStackTrace();
			return new ArrayList<>();
		} 
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
				logger.info("A REIMBURSEMENT IS INSERTED TO THE RDS");
				return true;
			} else {
				logger.debug("ISSUES WITH INSERTING A REIMBURSEMENT NON EXCEPTION");
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES WITH INSERTING A REIMBURSEMENT");
			e.printStackTrace();
			return false;
		}
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
				logger.info("A REIMBURSEMENT IS DELETED FROM THE RDS");
				return true;
			} else {
				logger.debug("ISSUES WITH DELETING A REIMBURSEMENT NON EXCEPTION");
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES WITH DELETING A REIMBURSEMENT");
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateStatus(String id, String newStatus) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			int statementIndex = 0;
			String command = "UPDATE reimbursments SET status=? WHERE id=?";
			
			PreparedStatement statement;
			statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, newStatus);
			statement.setString(++statementIndex, id);	
			
			if(statement.executeUpdate() > 0) {
				logger.info("THE STATUS OF A REIMBURSEMENT IS UPDATED IN THE RDS");
				return true;
			} else {
				logger.debug("ISSUES WITH UPDATING THE STATUS OF A REIMBURSEMENT NON EXCEPTION");
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("ISSUES WITH UPDATING THE STATUS OF A REIMBURSEMENT");
			e.printStackTrace();
			return false;
		}
	}

	
	
}