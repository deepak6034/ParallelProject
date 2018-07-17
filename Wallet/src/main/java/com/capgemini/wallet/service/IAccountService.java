package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;

public interface IAccountService {

	public int addAccountDetails(CustomerDetails customerDetails);

	public boolean getLogin();

	public void showBalance();

	public boolean deposit(int balance);

	public boolean withdraw(int balance);

	public boolean fundTransfer(String recieverAccountNumber, int balance);

	public void printTransactions();

}
