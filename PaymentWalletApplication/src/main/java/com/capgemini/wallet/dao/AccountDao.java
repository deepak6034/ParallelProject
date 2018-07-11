package com.capgemini.wallet.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.service.AccountValidation;

public class AccountDao implements IAccountDao {

	int bal;
	String str;
	String str1;
	public static Map<String, AccountDetails> account = new HashMap();
	AccountDetails details;
	AccountValidation validate = new AccountValidation();
	Scanner sc = new Scanner(System.in);

	public int addAccountDetails(AccountDetails details) {

		account.put(details.getUsername(), details);
		return 1;
	}

	public boolean getLogin() {
		System.out.println("Enter your username: ");
		String username = sc.nextLine();

		System.out.println("Enter your password: ");
		String password = sc.nextLine();

		boolean flag = validate.validateLogin(username, password);

		if (flag) {
			details = account.get(username);
			return true;
		} else {
			details = null;
			return false;
		}

	}

	public void showBalance() {

		if (details != null) {

			System.out.println("Balance = " + details.getBalance());

		}
	}

	public boolean deposit(int amount) {

		if (details != null) {
			bal = details.getBalance() + amount;
			details.setBalance(bal);
			str = amount + " deposited";
			details.getTransactions().add(str);
			return true;
		} else {
			return false;
		}

	}

	public boolean withdraw(int amount) {

		if (details != null) {

			if (details.getBalance() < 500) {
				System.out.println("Can't withdraw ..balance is below 500");
			}

			else if (details.getBalance() >= amount) {

				bal = details.getBalance() - amount;
				details.setBalance(bal);
				System.out.println("Withdrawal Done");

				str = amount + " Withdrawn";
				details.getTransactions().add(str);

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

		if (details != null) {

			senderFlag = true;

			if (details.getBalance() < 500) {

				System.out.println("Can't transfer ..balance is below 500");
			}

			else if (details.getBalance() >= amount) {

				for (String uname : account.keySet()) {

					if (account.get(uname).getAcccountNumber().equals(recieverAccountNumber)) {

						recieverFlag = true;
						bal = details.getBalance() - amount;
						details.setBalance(bal);

						bal = account.get(uname).getBalance() + amount;
						account.get(uname).setBalance(bal);

						str = amount + " transferred to account number : " + recieverAccountNumber;
						str1 = amount + " deposited from account number :" + details.getAcccountNumber();
						details.getTransactions().add(str);
						account.get(uname).getTransactions().add(str1);

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

		if (details != null) {

			flag = true;

			System.out.println(details.getTransactions());

		}

	}
}
