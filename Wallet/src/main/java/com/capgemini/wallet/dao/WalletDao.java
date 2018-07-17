package com.capgemini.wallet.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.bean.CustomerDetails;
import com.capgemini.wallet.service.AccountValidation;

public class WalletDao implements IAccountDao {

	int bal;
	String str;
	String str1;
	public static Map<String, CustomerDetails> account = new HashMap();
	CustomerDetails customerDetails;
	String accountNumber;
	AccountValidation validate = new AccountValidation();
	CustomerDao customer = new CustomerDao();
	AccountDao account1 = new AccountDao();
	LoginDao login = new LoginDao();
	TransactionDao transaction = new TransactionDao();
	Scanner sc = new Scanner(System.in);

	public int addAccountDetails(CustomerDetails customerDetails1) {

	  //  transaction.addTransactionDetails(customerDetails);
		if(customer.addCustomerDetails(customerDetails1)==1 && account1.addAccountDetails(customerDetails1)==1 && login.addLoginDetails(customerDetails1)==1)
		return 1;
		else
			return 0;
	}

	public boolean getLogin() {

		System.out.println("Enter your username: ");
		String username = sc.nextLine();

		System.out.println("Enter your password: ");
		String password = sc.nextLine();

		if (login.authenticate(username, password))
			return true;
		else
			return false;

	}

	public void showBalance() {

		account1.showBalance();

	}

	public boolean deposit(int amount) {

		if (account1.deposit(amount)==1)
			return true;
		else
			return false;
	}

	public boolean withdraw(int amount) {

		if (account1.withdraw(amount)==1)
			return true;
		else
			return false;
		
		
	}

	public boolean fundTransfer(String recieverAccountNumber, int amount) {

		boolean senderFlag = false;
		boolean recieverFlag = false;

		if (customerDetails != null) {

			senderFlag = true;

			if (customerDetails.getAccountDetails().getBalance() < 500) {

				System.out.println("Can't transfer ..balance is below 500");
			}

			else if (customerDetails.getAccountDetails().getBalance() >= amount) {

				for (String uname : account.keySet()) {

					if (account.get(uname).getAccountDetails().getAcccountNumber().equals(recieverAccountNumber)) {

						recieverFlag = true;
						bal = customerDetails.getAccountDetails().getBalance() - amount;
						customerDetails.getAccountDetails().setBalance(bal);

						bal = account.get(uname).getAccountDetails().getBalance() + amount;
						account.get(uname).getAccountDetails().setBalance(bal);

						str = amount + " transferred to account number : " + recieverAccountNumber;
						str1 = amount + " deposited from account number :"
								+ customerDetails.getAccountDetails().getAcccountNumber();
						customerDetails.getAccountDetails().getLoginDetails().getTransaction().getTransactions()
								.add(str);
						account.get(uname).getAccountDetails().getLoginDetails().getTransaction().getTransactions()
								.add(str1);

					}
				}
			}

			else {
				System.out.println("Insufficient Funds");
			}

		}

		if (!senderFlag) {
			System.out.println("Wrong Sender Details");
		}

		if (!recieverFlag && senderFlag) {
			System.out.println("Wrong Reciever Account Number");
		}

		if (senderFlag && recieverFlag)
			return true;
		else
			return false;

	}

	public void printTransactions() {
		boolean flag = false;

		if (customerDetails != null) {

			flag = true;

			System.out
					.println(customerDetails.getAccountDetails().getLoginDetails().getTransaction().getTransactions());

		}

	}
}
