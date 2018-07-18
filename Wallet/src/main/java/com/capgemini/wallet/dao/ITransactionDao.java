package com.capgemini.wallet.dao;

import com.capgemini.wallet.bean.CustomerDetails;

public interface ITransactionDao {
	
	public int addTransactionDetails(CustomerDetails customerDetails);
	public void addTransactions(String transaction);
	public void printTransactions();

}
