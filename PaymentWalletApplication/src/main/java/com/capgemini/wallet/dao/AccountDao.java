package com.capgemini.wallet.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.bean.CustomerDetails;
import com.capgemini.wallet.service.AccountValidation;

public class AccountDao implements IAccountDao {

	int bal;
	String str;
	String str1;
	public static Map<String, CustomerDetails> account = new HashMap();
	CustomerDetails customerDetails;
	AccountValidation validate = new AccountValidation();
	Scanner sc = new Scanner(System.in);

	public int addAccountDetails(CustomerDetails customerDetails) {

		account.put(customerDetails.getAccountDetails().getLoginDetails().getUsername(), customerDetails);
		return 1;
	}

	public boolean getLogin() {
		System.out.println("Enter your username: ");
		String username = sc.nextLine();

		System.out.println("Enter your password: ");
		String password = sc.nextLine();

		boolean flag = validate.validateLogin(username, password);

		if (flag) {
			customerDetails = account.get(username);
			return true;
		} else {
			customerDetails = null;
			return false;
		}

	}

	public void showBalance() {

		if (customerDetails != null) {

			System.out.println("Balance = " + customerDetails.getAccountDetails().getBalance());

		}
	}

	public boolean deposit(int amount) {

		if (customerDetails != null) {
			bal = customerDetails.getAccountDetails().getBalance() + amount;
			customerDetails.getAccountDetails().setBalance(bal);
			str = amount + " deposited";
			customerDetails.getAccountDetails().getLoginDetails().getTransaction().getTransactions().add(str);
			return true;
		} else {
			return false;
		}

	}

	public boolean withdraw(int amount) {

		if (customerDetails != null) {

			if (customerDetails.getAccountDetails().getBalance() < 500) {
				System.out.println("Can't withdraw ..balance is below 500");
			}

			else if (customerDetails.getAccountDetails().getBalance() >= amount) {

				bal = customerDetails.getAccountDetails().getBalance() - amount;
				customerDetails.getAccountDetails().setBalance(bal);
				System.out.println("Withdrawal Done");

				str = amount + " Withdrawn";
				customerDetails.getAccountDetails().getLoginDetails().getTransaction().getTransactions().add(str);

			}

			else {
				System.out.println("Insufficient Funds");
			}
			return true;
		} else {
			return false;
		}
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
