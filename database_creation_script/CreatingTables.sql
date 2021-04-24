create table employees (
	firstName varchar(50) not null, 
	lastName varchar(50) not null,
	email varchar(200) primary key,
	password varchar(24) not null,
	isManager boolean
);


create table reimbursments (
	id varchar(36) primary key,
	employeeId varchar(200),
	amount DOUBLE PRECISION not null,
	typeof varchar(7) not null,
	description varchar(1000),
	status varchar(8) not null,
	submissiondate Date not null,
	CONSTRAINT fk_employee
      FOREIGN KEY(employeeId) 
	  REFERENCES employees(email)
	  ON DELETE CASCADE
);


