package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;
import com.capgemini.wallet.dao.AccountDao;
import com.capgemini.wallet.dao.IAccountDao;

public class AccountService implements IAccountService{

	IAccountDao dao = new AccountDao();

	public int addAccountDetails(CustomerDetails customerDetails)
	{
		return dao.addAccountDetails(customerDetails);
	}
	public int showBalance()
	{
		return dao.showBalance();
	}
	public int deposit(int amount)
	{
		return dao.deposit(amount);
	}
	public int withdraw(int amount)
	{
		return dao.withdraw(amount);
	}
	public int fundTransfer(String recieverAccountNumber, int amount)
	{
		return dao.fundTransfer(recieverAccountNumber, amount);
	}

}
