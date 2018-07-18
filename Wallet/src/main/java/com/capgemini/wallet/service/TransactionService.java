package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;
import com.capgemini.wallet.dao.ITransactionDao;
import com.capgemini.wallet.dao.TransactionDao;

public class TransactionService implements ITransactionService{
	
	ITransactionDao dao = new TransactionDao();

	public int addTransactionDetails(CustomerDetails customerDetails)
	{
		return dao.addTransactionDetails(customerDetails);
	}
	public void addTransactions(String transaction)
	{
		dao.addTransactions(transaction);
	}
	public void printTransactions()
	{
		dao.printTransactions();
	}

}
