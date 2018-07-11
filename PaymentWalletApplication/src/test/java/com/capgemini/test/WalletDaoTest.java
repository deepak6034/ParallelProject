package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.dao.AccountDao;

public class WalletDaoTest {

	AccountDetails details = new AccountDetails();
	AccountDao dao = new AccountDao();

	@Test
	public void testAddAccountDetails() {

		System.out.println("test case from dao to add");
		assertEquals(1, dao.addAccountDetails(details));

	}

	@Test
	public void testDeposit() {

		System.out.println("test case from dao to deposit");
		assertEquals(false, dao.deposit(5000));

	}

	@Test
	public void testWithdraw() {

		System.out.println("test case from dao to withdraw");
		assertEquals(false, dao.withdraw(5000));
	}

	@Test
	public void testShowBalance() {

		System.out.println("test case from dao to show balance");
		assertEquals(false, dao.showBalance());
	}

	@Test
	public void testFundTranfer() {

		System.out.println("test case from dao to transfer fund");
		assertEquals(false, dao.fundTransfer("2343589", 7000));
	}

	public void testPrintTransactions() {

		System.out.println("test case from dao to print transactions");
		assertEquals(false, dao.printTransactions());

	}

}
