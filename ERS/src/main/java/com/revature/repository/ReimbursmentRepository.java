package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;
import com.revature.model.Reimbursment;

public interface ReimbursmentRepository {
	
	//DQL
	public List<Reimbursment> selectAllReimbursments(); //NIY // service for financial managers only
	public List<Reimbursment> selectByEmployeeId(String employeeId); //service for employee and for financial managers
	public List<Reimbursment> selectByType(String type); //service for employee and for financial managers
	public List<Reimbursment> selectByStatus(String status); //service for employee and for financial managers
	
	public List<Reimbursment> selectByEmployeeType(String employeeId, String type); //NIY // service for financial managers only
	public List<Reimbursment> selectByEmployeeStatus(String employeeId, String status); //NIY // service for financial managers only
	
	//DML
	public boolean insert(Reimbursment reimbursment); // service for employees
	public boolean delete(String reimbursmentId); //NIY // service for financial managers only 
	
	// Do we want to include updates to the reimbursement requests?
//	public boolean updateAmount(String id, Double newAmount); //NIY
//	public boolean updateType(String id, String newType); //NIY
	public boolean updateStatus(String id, String newStatus);
//	public boolean updateDescription(String id, String newDescription); //NIY
}