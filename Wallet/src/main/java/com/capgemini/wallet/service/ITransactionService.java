package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;

public interface ITransactionService {

	public int addTransactionDetails(CustomerDetails customerDetails);
	public void addTransactions(String transaction);
	public void printTransactions();

}
