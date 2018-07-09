package com.capgemini.wallet.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.capgemini.wallet.bean.AccountDetails;

public class AccountServiceDao {

	int bal;
	String str;
	String str1;
	Map<String, AccountDetails> account = new HashMap();

	public int addAccountDetails(AccountDetails details) {

		account.put(details.getAcccountNumber(), details);
		return 1;
	}

	public boolean deposit(String username, String password, String accountNumber, int balance) {
		boolean flag = false;

		for (String accNo : account.keySet()) {
			if (accountNumber.equals(accNo)) {

				if (account.get(accountNumber).getUsername().equals(username)) {

					if (account.get(accountNumber).getPassword().equals(password)) {

						bal = account.get(accountNumber).getBalance() + balance;
						account.get(accountNumber).setBalance(bal);
						flag = true;

						str = balance + " deposited";
						account.get(accountNumber).getTransactions().add(str);

					}
				}
			}

		}

		return flag;

	}

	public boolean withdraw(String username, String password, String accountNumber, int balance) {

		boolean flag = false;

		for (String accNo : account.keySet()) {
			if (accountNumber.equals(accNo)) {

				if (account.get(accountNumber).getUsername().equals(username)) {

					if (account.get(accountNumber).getPassword().equals(password)) {

						flag = true;

						if (account.get(accountNumber).getBalance() < 500) {

							System.out.println("Can't withdraw ..balance is below 500");
						}

						else if (account.get(accountNumber).getBalance() >= balance) {

							bal = account.get(accountNumber).getBalance() - balance;
							account.get(accountNumber).setBalance(bal);
							System.out.println("Withdrawal Done");

							str = balance + " Withdrawn";
							account.get(accountNumber).getTransactions().add(str);

						}

						else {
							System.out.println("Insufficient Funds");
						}

					}
				}
			}

		}

		return flag;

	}

	public boolean showBalance(String username, String password, String accountNumber) {

		boolean flag = false;

		for (String accNo : account.keySet()) {
			if (accountNumber.equals(accNo)) {

				if (account.get(accountNumber).getUsername().equals(username)) {

					if (account.get(accountNumber).getPassword().equals(password)) {

						flag = true;
						System.out.println(account.get(accountNumber).getBalance());
					}
				}
			}
		}

		return flag;

	}

	public boolean fundTransfer(String username, String password, String SenderAccountNumber,
			String RecieverAccountNumber, int balance) {

		boolean senderFlag = false;
		boolean recieverFlag = false;

		for (String accNo : account.keySet()) {

			if (SenderAccountNumber.equals(accNo)) {

				if (account.get(SenderAccountNumber).getUsername().equals(username)) {

					if (account.get(SenderAccountNumber).getPassword().equals(password)) {

						senderFlag = true;

						if (account.get(SenderAccountNumber).getBalance() < 500) {

							System.out.println("Can't transfer ..balance is below 500");
						}

						else if (account.get(SenderAccountNumber).getBalance() >= balance) {

							for (String accNo1 : account.keySet()) {

								if (RecieverAccountNumber.equals(accNo1)) {

									recieverFlag = true;
									bal = account.get(SenderAccountNumber).getBalance() - balance;
									account.get(SenderAccountNumber).setBalance(bal);

									bal = account.get(RecieverAccountNumber).getBalance() + balance;
									account.get(RecieverAccountNumber).setBalance(bal);

									str = balance + " transferred to account number : " + RecieverAccountNumber;
									str1 = balance + " deposited from account number :" + SenderAccountNumber;
									account.get(SenderAccountNumber).getTransactions().add(str);
									account.get(RecieverAccountNumber).getTransactions().add(str1);

								}
							}
						}

						else {
							System.out.println("Insufficient Funds");
						}

					}

				}
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

	public boolean printTransactions(String username, String password, String accountNumber) {
		boolean flag = false;

		for (String accNo : account.keySet()) {
			if (accountNumber.equals(accNo)) {

				if (account.get(accountNumber).getUsername().equals(username)) {

					if (account.get(accountNumber).getPassword().equals(password)) {

						flag = true;

						System.out.println(account.get(accountNumber).getTransactions());

					}
				}
			}
		}

		return flag;

	}
}
