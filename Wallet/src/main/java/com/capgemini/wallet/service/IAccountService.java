package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;

public interface IAccountService {

	public int addAccountDetails(CustomerDetails customerDetails);

	public int showBalance();

	public int deposit(int amount);

	public int withdraw(int amount);

	public int fundTransfer(String recieverAccountNumber, int amount);

}
