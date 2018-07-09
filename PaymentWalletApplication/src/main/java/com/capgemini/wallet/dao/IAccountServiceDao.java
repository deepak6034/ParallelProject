package com.capgemini.wallet.dao;

import com.capgemini.wallet.bean.AccountDetails;

public interface IAccountServiceDao {

	public int addAccountDetails(AccountDetails details);

	public boolean deposit(String username, String password, String accountNumber, int balance);

	public boolean withdraw(String username, String password, String accountNumber, int balance);

	public boolean showBalance(String username, String password, String accountNumber);

	public boolean fundTransfer(String username, String password, String SenderAccountNumber,
			String RecieverAccountNumber, int balance);

	public boolean printTransactions(String username, String password, String accountNumber);
}
