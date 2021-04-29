package com.revature.controller;

import com.revature.repository.ReimbursmentRepositoryJdbc;

public class FinancialManagerControllerImpl implements FinancialManagerController {
	
	@Override
	public int getClaimCount() {
		// TODO Auto-generated method stub
		return ReimbursmentRepositoryJdbc.getInstance().getClaim().size();
	}

}
