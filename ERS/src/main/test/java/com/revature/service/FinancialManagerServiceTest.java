package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.revature.model.Reimbursment;

class FinancialManagerServiceTest {

	@Test
	void testGetClaimCount() {
		// You have to set uo the curClaimCount manually given the current number of claims
		// I don't think this is the right way to do it though
		int curClaimCount = 20;
		assertEquals(FinancialManagerServiceImpl.getInstance().getClaimCount(), curClaimCount);
	}

	@Test
	void testGetStatusById() {
		//manually get ids of different reimbuirsements wth different status
		
		String pendingId = "af1e4e2a-982d-4300-9b0e-613279735f89";
		String approvedId = "4daa7bb4-6b0a-4e49-bd85-1645f6ca9e6b";
		String deniedId = "2876d8b7-e3a1-472f-966f-c5438bf704b3";
		
		assertEquals(FinancialManagerServiceImpl.getInstance().getStatusById(pendingId), "pending");
		assertEquals(FinancialManagerServiceImpl.getInstance().getStatusById(approvedId), "approved");
		assertEquals(FinancialManagerServiceImpl.getInstance().getStatusById(deniedId), "denied");
	
		assertNotEquals(FinancialManagerServiceImpl.getInstance().getStatusById(pendingId), "approved");
		assertNotEquals(FinancialManagerServiceImpl.getInstance().getStatusById(approvedId), "denied");
		assertNotEquals(FinancialManagerServiceImpl.getInstance().getStatusById(deniedId), "pending");
	}

	@Test
	void testSetStatusById() {
		//Front end js doesn't allow changing the status of approved or denied claims
		//Backend doesn't allow changing the status to anything other than either approved or denied, let't test this
		//we can only set status of a pending reimbursement to be approved or denied
		String pendingId1 = "7f8f5200-564b-462f-a154-c30a7d879b29";
		String pendingId2 = "07e7b2c9-e7ed-4ecc-9f7e-3acbc14d6c1c";
		assertTrue(FinancialManagerServiceImpl.getInstance().setStatusById(pendingId1, "approved"));
		assertFalse(FinancialManagerServiceImpl.getInstance().setStatusById(pendingId2, "anything"));
	}

	@Test
	void testListEmployees() {
		int curEmployeeSize = 23;
		assertEquals(FinancialManagerServiceImpl.getInstance().listEmployees().size(), curEmployeeSize);
		assertNotEquals(FinancialManagerServiceImpl.getInstance().listEmployees().size(), curEmployeeSize  + 1);
	}

	@Test
	void testShowReimbursements() {
		assertEquals(FinancialManagerServiceImpl.getInstance().showReimbursements("all", "all").size(), FinancialManagerServiceImpl.getInstance().getClaimCount());
	}

}
