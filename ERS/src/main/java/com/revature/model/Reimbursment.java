package com.revature.model;

import java.util.UUID;
import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;

public class Reimbursment {
	private String id; //primary key
	private String employeeId; //Foreign key, it is the username/email of the respective employee
	private Double amount;
	private String type; //lodging, travel, food, other
	private String description;
	private String status; //pending, approved, denied
	private LocalDate  submissionDate;
	private Path imagePath; //path of uploaded receipt image
	
	public Reimbursment(String employeeId, Double amount, String type, String description) {
		super();
		this.id = UUID.randomUUID().toString();
		this.employeeId = employeeId;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.status = "pending"; //any reimbursement request starts as pending
		this.submissionDate = LocalDate.now();
		
		String cwd = System.getenv("ersImagesFolder");
		File fileToSave = new File(cwd + this.id);
		this.imagePath = fileToSave.toPath();
	}
	
	//Overloaded constructor for retrieving objects from the database
	public Reimbursment(String id, String employeeId, Double amount, String type, String status, LocalDate submissionDate, String description) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.amount = amount;
		this.type = type;
		this.description = description;
		this.status = status; 
		this.submissionDate = submissionDate;
		
		String cwd = System.getenv("ersImagesFolder");
		File fileToSave = new File(cwd + this.id);
		this.imagePath = fileToSave.toPath();
	}

	public String getId() {
		return id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}
	
	public Path getImagePath() {
		return imagePath;
	}

	@Override
	public String toString() {
		return "Reimbursment [id=" + id + ", employeeId=" + employeeId + ", amount=" + amount + ", type=" + type
				+ ", description=" + description + ", status=" + status + ", submissionDate=" + submissionDate + "]";
	}

}