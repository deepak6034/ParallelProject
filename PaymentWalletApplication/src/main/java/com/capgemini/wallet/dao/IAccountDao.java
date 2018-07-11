package com.capgemini.wallet.dao;

import com.capgemini.wallet.bean.AccountDetails;

public interface IAccountDao {

	public int addAccountDetails(AccountDetails details);

	public boolean deposit(int balance);

	public boolean withdraw(int balance);

	public boolean showBalance();

	public boolean fundTransfer(String recieverAccountNumber, int balance);

	public boolean printTransactions();

}
