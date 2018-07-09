package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.dao.AccountServiceDao;

public class AccountServiceDaoTest {

	AccountDetails details = new AccountDetails();
	AccountServiceDao dao = new AccountServiceDao();

	@Test
	public void testAddAccountDetails() {

		System.out.println("test case from dao to add");
		assertEquals(1, dao.addAccountDetails(details));

	}

	@Test
	public void testDeposit() {

		System.out.println("test case from dao to deposit");
		assertEquals(false, dao.deposit("deepak_12", "deepak@12", "1212345", 5000));

	}

	@Test
	public void testWithdraw() {
		
		System.out.println("test case from dao to withdraw");
		assertEquals(false, dao.withdraw("deepak_12", "deepak@12", "1212345", 5000));
	}

	@Test
	public void testShowBalance() {
		
		System.out.println("test case from dao to show balance");
		assertEquals(false, dao.showBalance("deepak_12", "deepak@12", "1212345"));
	}

	@Test
	public void testFundTranfer() {
		
		System.out.println("test case from dao to transfer fund");
		assertEquals(false, dao.fundTransfer("deepak_12", "deepak@12", "1212345", "2343589", 7000));
	}
/*	@Test
	public void testPrintTransactions() {

		System.out.println("test case from dao to print transactions");
		assertEquals(false, dao.printTransactions("deepak_12", "deepak@12", "1212345"));
		
	}*/

}
