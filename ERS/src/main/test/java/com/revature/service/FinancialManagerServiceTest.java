package com.revature.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class FinancialManagerServiceTest {

	@Test
	void testGetClaimCount() {
		// You have to set uo the curClaimCount manually given the current number of claims
		// I don't think this is the right way to do it though
		int curClaimCount = 6;
		assertEquals(FinancialManagerServiceImpl.getInstance().getClaimCount(), curClaimCount);
	}

	@Test
	void testGetStatusById() {
		//manually get ids of different reimbuirsements wth different status
		
		String pendingId = "d1e5425b-e0ce-4519-8428-03eadf7189ae";
		String approvedId = "edcbd4c6-91f6-49b4-8e98-1211f03b2538";
		String deniedId = "3c878d78-2b36-4b50-a4ba-2bc0e5acdb5f";
		
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
		String pendingId1 = "f0ddb1e0-4865-4bfe-a831-b7cfe8abcbd5";
		String pendingId2 = "7ed6269a-b5b7-4b2e-b8c2-d4c3fc983624";
		assertTrue(FinancialManagerServiceImpl.getInstance().setStatusById(pendingId1, "approved"));
		assertFalse(FinancialManagerServiceImpl.getInstance().setStatusById(pendingId2, "anything"));
	}

	@Test
	void testListEmployees() {
		int curEmployeeSize = 7;
		assertEquals(FinancialManagerServiceImpl.getInstance().listEmployees().size(), curEmployeeSize);
		assertNotEquals(FinancialManagerServiceImpl.getInstance().listEmployees().size(), curEmployeeSize  + 1);
	}

	@Test
	void testShowReimbursements() {
		assertEquals(FinancialManagerServiceImpl.getInstance().showReimbursements("all", "all").size(), FinancialManagerServiceImpl.getInstance().getClaimCount());
	}

}
