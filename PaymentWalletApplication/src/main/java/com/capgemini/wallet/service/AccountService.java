package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.dao.AccountServiceDao;

public class AccountService {

	AccountServiceDao dao = new AccountServiceDao();

	public int addAccountDetails(AccountDetails details) {

		return dao.addAccountDetails(details);
	}

	public boolean deposit(String username, String password, String accountNumber, int balance) {

		return dao.deposit(username, password, accountNumber, balance);
	}

	public boolean withdraw(String username, String password, String accountNumber, int balance) {

		return dao.withdraw(username, password, accountNumber, balance);
	}

	public boolean showBalance(String username, String password, String accountNumber) {
		return dao.showBalance(username, password, accountNumber);
	}

	public boolean fundTranfer(String username, String password, String SenderAccountNumber,
			String RecieverAccountNumber, int balance) {

		return dao.fundTransfer(username, password, SenderAccountNumber, RecieverAccountNumber, balance);
	}

	public boolean printTransactions(String username, String password, String accountNumber) {

		return dao.printTransactions(username, password, accountNumber);
	}
}
