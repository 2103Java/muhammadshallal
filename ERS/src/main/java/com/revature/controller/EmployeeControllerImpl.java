package com.revature.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.revature.ajax.ClientMessage;

import com.revature.model.*;
import com.revature.service.EmployeeServiceImpl;

public class EmployeeControllerImpl implements EmployeeController{
	
		//Singleton design
		private static EmployeeController employeeController;
		
		private EmployeeControllerImpl() {}
		
		public static EmployeeController getInstance() {
			if(employeeController == null) {
				employeeController = new EmployeeControllerImpl();
			}
			
			return employeeController;
		}
		
		//configure logger
		static final Logger logger = Logger.getLogger(EmployeeControllerImpl.class);
		

		//Implementations without servlets from the frontend
		@Override
		public ClientMessage register(String firstname, String lastname, String username) {
			
			Employee employee = new Employee(firstname, lastname, username, "hashed_password");
			
			if (EmployeeServiceImpl.getInstance().registerEmployee(employee)) {
				logger.info("REGISTRATION SUCCESSFUL BY " + username);
				return new ClientMessage("REGISTRATION SUCCESSFUL");
			} else {
				logger.info("REGISTRATION UNSUCCESSFUL BY " + username);
				return new ClientMessage("SOMETHING WENT WRONG DURING REGISTRATION");
			}
		}

		@Override
		public ClientMessage unregister(String username) {
			
			if (EmployeeServiceImpl.getInstance().unregisterEmployee(username) == true){
				logger.info("UNREGISTRATION SUCCESSFUL BY " + username);
				return new ClientMessage("UNREGISTRATION SUCCESSFUL");
			} else {
				logger.info("UNREGISTRATION UNSUCCESSFUL BY " + username);
				return new ClientMessage("SOMETHING WENT WRONG DURING UNREGISTRATION");
			}
		}

		@Override
		public ClientMessage isRegistered(String username) {
			if(EmployeeServiceImpl.getInstance().employeeExists(username) == true) {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", ALREADY EXISTS");
				return new ClientMessage("AN EMPLOYEE WITH THIS USERNAME ALREADY EXISTS");
			} else {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", DOES NOT EXIST");
				return new ClientMessage("NO EMPLOYEE WITH THIS USERNAME EXISTS");
			}
		}

		@Override
		public ClientMessage viewAllEmployees() {
			List<Employee> employeeList = EmployeeServiceImpl.getInstance().listEmployees();
			
			logger.info("LIST OF ALL EMPLOYEES IS VIEWED");
			//view employeeList, but for the time being, just print them in console
			for(Employee employee : employeeList) {
				System.out.println(employee.toString());
			}
			
			return new ClientMessage("LIST OF ALL EMPLOYEES IS VIEWED");
		}

		@Override
		public ClientMessage login(String username, String password) {
			if(EmployeeServiceImpl.getInstance().login(username, password) == true) {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", JUST LOGGEDIN");
				return new ClientMessage("AN EMPLOYEE WITH THIS USERNAME ALREADY LOGGEDIN");
			} else {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", UNABLE TO LOGIN");
				return new ClientMessage("AN EMPLOYEE WITH THIS USERNAME UNABLE TO LOGIN");
			}
		}

		@Override
		public ClientMessage logout(String username, String password) {
			// request.getSession().invalidate();
			if(EmployeeServiceImpl.getInstance().logout(username, password) == true) {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", JUST LOGGEDOUT");
				return new ClientMessage("AN EMPLOYEE WITH THIS USERNAME ALREADY LOGGEDOUT");
			} else {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + username + ", UNABLE TO LOGOUT");
				return new ClientMessage("AN EMPLOYEE WITH THIS USERNAME UNABLE TO LOGOUT");
			}
		}

		@Override
		public ClientMessage submitReimbursement(HttpServletRequest request) {
			
			Reimbursment reimbursment =  new Reimbursment(request.getParameter("username"), 
					                                      Double.parseDouble(request.getParameter("Amount")), 
					                                      request.getParameter("Category"),
					                                      request.getParameter("Description"));
			
			// https://happycoding.io/tutorials/java-server/uploading-files
			//Handle uploaded receipts by copying the InputStream into the receipts directory
			try {
				Part filePart = request.getPart("fileselect");
				InputStream fileContent = filePart.getInputStream();
				Files.copy(fileContent, reimbursment.getImagePath(), StandardCopyOption.REPLACE_EXISTING);
				fileContent.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Retrieves <input type="file" name="fileselect">
			
			
			if (EmployeeServiceImpl.getInstance().submitReimbursment(reimbursment)) {
				logger.info("REIMBURSEMENET SUBMISSION SUCCESSFUL: " + reimbursment.getId() + " BY " + reimbursment.getEmployeeId());
				return new ClientMessage("REIMBURSEMENET SUBMISSION SUCCESSFUL");
			} else {
				logger.info("REIMBURSEMENET SUBMISSION UNSUCCESSFUL: " + reimbursment.getId() + " BY " + reimbursment.getEmployeeId());
				return new ClientMessage("REIMBURSEMENET SUBMISSION UNSUCCESSFUL");
			}
		}
		
		@Override
		public List<Reimbursment> showEmployeeReimbursements(HttpServletRequest request) {
			List<Reimbursment> employeeReimbursmentList = EmployeeServiceImpl.getInstance().showMyPreviousReimbursments(request.getParameter("username"));
			
			logger.info("LIST OF EMPLOYEE PREVIOUS REIMBURSEMENETS IS VIEWED");
			return employeeReimbursmentList;
		}

		@Override
		public int getEmployeeCount(HttpServletRequest request) {
			List<Reimbursment> employeeReimbursmentList = EmployeeServiceImpl.getInstance().showMyPreviousReimbursments(request.getParameter("username"));
			
			logger.info("GET EMPLOYEE COUNT");
			return employeeReimbursmentList.size();
		}
}



