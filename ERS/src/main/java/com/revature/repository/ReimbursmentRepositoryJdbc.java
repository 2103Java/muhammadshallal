package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			return null;
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
			return null;
		} 
	}
    
	@Override
	public List<Reimbursment> selectByTypeAndStatus(String type, String status) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
					
			int statementIndex = 0;
			String command = "SELECT * FROM reimbursments WHERE typeof like ? and status like ?";
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be inserted
			if (type.equals("all"))
				statement.setString(1, "%%");
			else statement.setString(1, type);
			if (status.equals("all"))
				statement.setString(2, "%%");
			else statement.setString(2, status);
			
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
			return null;
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
			return null;
		} 
	}
	
	
	@Override
	public List<Reimbursment> selectByEmployeeTypeAndStatus(String employeeId, String type, String status) {
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			String command = "SELECT * FROM reimbursments WHERE employeeId=? AND typeof like ? AND status like ?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(1, employeeId.toLowerCase());
			if (type.equals("all"))
				statement.setString(2, "%%");
			else statement.setString(2, type);
			if (status.equals("all"))
				statement.setString(3, "%%");
			else statement.setString(3, status);
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
			return null;
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
			return null;
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
	public Map<Reimbursment, String> getClaim() {
		Connection connection = null;
		Map<Reimbursment, String> map = new HashMap<Reimbursment, String>();
		try {
			connection = ConnectionUtil.getConnection();
			String command = "SELECT e.firstname, r.submissiondate, r.status, r.amount FROM "
					+ "employees e, reimbursments r where e.email = r.employeeid";
			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String empName = result.getString("firstname");
				Reimbursment reimbursment = new Reimbursment(
						null,
						null, 
						  result.getDouble("amount"),
						  null,
						  result.getString("status"),
						  result.getDate("submissiondate").toLocalDate(),
						  null);
				map.put(reimbursment, empName);
			}
			return map;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with getting reimbursment.");
			return new HashMap();
		}
	}

	@Override
	public String getStatusById(String id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			String command = "SELECT status FROM reimbursments WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			return result.getString("status");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with getting reimbursment.");
			return "Claim Not Found";
		}
	}

	@Override
	public boolean setStatusById(String id, String status) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionUtil.getConnection();
			String command = "update reimbursments set status = ? WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(1, status);
			statement.setString(2, id);
			statement.executeUpdate();
			return true;
			
		}catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with getting reimbursment.");
			return false;
		}
	}
	
}