package com.revature.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
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
		private static List<String> financeManagers = new ArrayList<String>();
		
		public static EmployeeController getInstance() {
			if(employeeController == null) {
				financeManagers.add("hshallal@gmail.com");
				financeManagers.add("abdul.moeed.ak@gmail.com");
				employeeController = new EmployeeControllerImpl();
			}
			
			return employeeController;
		}
		
		//configure logger
		static final Logger logger = Logger.getLogger(EmployeeControllerImpl.class);
		
		

		//Implementations without servlets from the frontend
		@Override
		public Employee login(HttpServletRequest request) {
			Employee loginResult = EmployeeServiceImpl.getInstance().login(request.getParameter("email"), request.getParameter("password"));
			if(loginResult != null) {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + request.getParameter("email") + ", JUST LOGGEDIN");
				return loginResult;
			} else {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + request.getParameter("email") + ", UNABLE TO LOGIN");
				return null;
			}
		}
		
		@Override
		public ClientMessage register(HttpServletRequest request) {
			boolean isEmailTaken = isRegistered(request.getParameter("email"));
			if(isEmailTaken == true) {
				logger.info("THIS EMAIL IS TAKEN:" + request.getParameter("email"));
				return new ClientMessage("EMAIL IS TAKEN");
			}
			
			boolean isFinanceManager = this.financeManagers.contains(request.getParameter("email"));
					
			Employee employee = new Employee(request.getParameter("firstname"), 
											 request.getParameter("lastname"), 
											 request.getParameter("email"), 
											 request.getParameter("password"), 
											 isFinanceManager);
			
			if (EmployeeServiceImpl.getInstance().registerEmployee(employee)) {
				logger.info("REGISTRATION SUCCESSFUL BY " + request.getParameter("email"));
				return new ClientMessage("REGISTRATION SUCCESSFUL");
			} else {
				logger.info("REGISTRATION UNSUCCESSFUL BY " + request.getParameter("email"));
				return new ClientMessage("SOMETHING WENT WRONG DURING REGISTRATION");
			}
		}

		@Override
		public ClientMessage unregister(String email) {
			
			if (EmployeeServiceImpl.getInstance().unregisterEmployee(email) == true){
				logger.info("UNREGISTRATION SUCCESSFUL BY " + email);
				return new ClientMessage("UNREGISTRATION SUCCESSFUL");
			} else {
				logger.info("UNREGISTRATION UNSUCCESSFUL BY " + email);
				return new ClientMessage("SOMETHING WENT WRONG DURING UNREGISTRATION");
			}
		}

		@Override
		public boolean isRegistered(String email) {
			boolean isEmailTaken = EmployeeServiceImpl.getInstance().employeeExists(email);
			if(isEmailTaken == true) {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + email + ", ALREADY EXISTS");
				return true;
			} else {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + email + ", DOES NOT EXIST");
				return false;
			}
		}

		@Override
		public ClientMessage logout(String email, String password) {
			// request.getSession().invalidate();
			if(EmployeeServiceImpl.getInstance().logout(email, password) == true) {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + email + ", JUST LOGGEDOUT");
				return new ClientMessage("AN EMPLOYEE WITH THIS USERNAME ALREADY LOGGEDOUT");
			} else {
				logger.info("AN EMPLOYEE WITH THIS USERNAME: " + email + ", UNABLE TO LOGOUT");
				return new ClientMessage("AN EMPLOYEE WITH THIS USERNAME UNABLE TO LOGOUT");
			}
		}

		@Override
		public ClientMessage submitReimbursement(HttpServletRequest request) {
			
			Reimbursment reimbursment =  new Reimbursment(request.getParameter("username"), 
                    Double.parseDouble(request.getParameter("Amount")), 
                    request.getParameter("category"),
                    request.getParameter("Description"));

			// https://happycoding.io/tutorials/java-server/uploading-files
			//Handle uploaded receipts by copying the InputStream into the receipts directory
			try {
			Part filePart = request.getPart("fileselect");
			
			System.out.println(filePart.getName());
			System.out.println(filePart.getContentType());
			System.out.println(filePart.getSubmittedFileName());
			System.out.println(filePart.getSize());
			
			InputStream fileContent = filePart.getInputStream();
			OutputStream fileOutput = new FileOutputStream(reimbursment.getImagePath().toString());
			fileOutput.write(fileContent.read());
			fileContent.close();
			fileOutput.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} // Retrieves <input type="file" name="fileselect">
			boolean submissionResult = EmployeeServiceImpl.getInstance().submitReimbursment(reimbursment);
			if (submissionResult == true) {
				logger.info("REIMBURSEMENET SUBMISSION SUCCESSFUL: " + reimbursment.getId() + " BY " + reimbursment.getEmployeeId());
				return new ClientMessage("REIMBURSEMENET SUBMISSION SUCCESSFUL");
			} else {
				logger.info("REIMBURSEMENET SUBMISSION UNSUCCESSFUL: " + reimbursment.getId() + " BY " + reimbursment.getEmployeeId());
				return new ClientMessage("REIMBURSEMENET SUBMISSION UNSUCCESSFUL");
			}
		}
		
		@Override
		public List<Reimbursment> showEmployeeReimbursements(HttpServletRequest request) {
			List<Reimbursment> employeeReimbursmentList = EmployeeServiceImpl.getInstance().showMyPreviousReimbursments(request.getParameter("username"), request.getParameter("filter"));
			
			logger.info("LIST OF EMPLOYEE PREVIOUS REIMBURSEMENETS IS VIEWED");
			return employeeReimbursmentList;
		}

}



