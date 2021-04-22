package com.revature.model;

public class Employee {
	
	private long id;
	private String firstName;
	private String lastName;
	private String ssn;
	private String address;
	private boolean manager;
	private String phoneNumber;
	private String email;
	private String password;
	
	public Employee() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Employee(long id, String firstName, String lastName, String ssn, String address, boolean manager,
			String phoneNumber, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.address = address;
		this.manager = manager;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isManager() {
		return manager;
	}
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
