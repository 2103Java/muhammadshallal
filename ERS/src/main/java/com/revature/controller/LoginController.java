package com.revature.controller;

import com.revature.model.Employee;

public interface LoginController {
	
	Employee authenticate (String email, String password);
	
}
