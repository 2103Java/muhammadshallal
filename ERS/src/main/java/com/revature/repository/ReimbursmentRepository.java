package com.revature.repository;

import java.util.List;
import java.util.Map;
import com.revature.model.Reimbursment;

public interface ReimbursmentRepository {
	
	//DQL
	public List<Reimbursment> selectAllReimbursments(); //NIY // service for financial managers only
	public List<Reimbursment> selectByEmployeeId(String employeeId); //service for employee and for financial managers
	public List<Reimbursment> selectByTypeAndStatus(String type, String status); //service for employee and for financial managers
	public List<Reimbursment> selectByStatus(String status); //service for employee and for financial managers
	
	public List<Reimbursment> selectByEmployeeTypeAndStatus(String employeeId, String type, String status); //NIY // service for financial managers only
	public List<Reimbursment> selectByEmployeeStatus(String employeeId, String status); //NIY // service for financial managers only
	
	//DML
	public boolean insert(Reimbursment reimbursment); // service for employees
	public boolean delete(String reimbursmentId); //NIY // service for financial managers only
	
	public String getStatusById(String id);
	public boolean setStatusById(String id, String status);
	
	public Map<Reimbursment, String> getClaim();
	
}