package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.dao.AccountDao;
import com.capgemini.wallet.dao.IAccountDao;

public class AccountService implements IAccountService {

	IAccountDao dao = new AccountDao();

	public int addAccountDetails(AccountDetails details) {
		return dao.addAccountDetails(details);
	}

	public boolean getLogin() {
		return dao.getLogin();
	}

	public void showBalance() {
		dao.showBalance();
	}

	public boolean deposit(int balance) {
		return dao.deposit(balance);
	}

	public boolean withdraw(int balance) {
		return dao.withdraw(balance);
	}

	public boolean fundTransfer(String recieverAccountNumber, int balance) {
		return dao.fundTransfer(recieverAccountNumber, balance);
	}

	public void printTransactions() {

		dao.printTransactions();
	}

}
