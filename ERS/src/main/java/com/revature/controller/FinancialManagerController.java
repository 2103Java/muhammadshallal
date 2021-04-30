package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Reimbursment;

public interface FinancialManagerController {
	public int getClaimCount();
	public int getEmployeeCount();
	public String getStatusById(HttpServletRequest request);
	public boolean setStatusById(HttpServletRequest request);
	public List<Reimbursment> showReimbursements(HttpServletRequest request);
	public boolean modifyStatus(HttpServletRequest request);
}
