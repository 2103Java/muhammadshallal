package com.revature.model;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String email; // primary key
	private String password; //hashed?
	private boolean isManager;
	
	public Employee() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Employee(String firstName, String lastName, String username, String password, boolean manager) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = username;
		this.password = password;
		this.isManager = manager;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public boolean getIsManager() {
		return isManager;
	}
	
	public void setIsManager(boolean manager) {
		this.isManager = manager;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
