package com.capgemini.wallet.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.bean.CustomerDetails;
import com.capgemini.wallet.bean.Login;
import com.capgemini.wallet.bean.Transaction;
import com.capgemini.wallet.exception.AccountNotCreatedException;
import com.capgemini.wallet.service.AccountValidation;
import com.capgemini.wallet.service.AccountService;
import com.capgemini.wallet.service.IAccountService;

public class Client {

	public static void main(String[] args) {

		int bal, choice;
		boolean flag;
		int result = 0, initialBalance = 0;
		Scanner sc = new Scanner(System.in);
		IAccountService service = new AccountService();
		AccountValidation validate = new AccountValidation();

		while (true) {

			CustomerDetails customerDetails = new CustomerDetails();
			AccountDetails accountDetails = new AccountDetails();
			Login loginDetails = new Login();
			Transaction transaction = new Transaction();

			System.out.println("\n     Welcome to Technofitz Payment Wallet Application \n");
			System.out.println("1. Create Account: ");
			System.out.println("2. Login ");
			System.out.println("3. Exit ");

			choice = sc.nextInt();

			sc.nextLine();

			switch (choice) {

			case 1:

				System.out.println("Enter Name of the Account Holder: ");
				String accountHolderName = sc.nextLine();

				System.out.println("Enter Phone Number: ");
				String phoneNumber = sc.nextLine();

				boolean isValidNumber = validate.validatePhoneNumber(phoneNumber);

				if (!isValidNumber) {
					System.out.println("Wrong input ..try again");
					continue;
				}

				System.out.println("Enter Email: ");
				String email = sc.nextLine();

				boolean isValidateEmail = validate.validateEmail(email);

				if (!isValidateEmail) {
					System.out.println("Wrong input ..try again");
					continue;
				}

				System.out.println("Enter Age: ");
				int age = sc.nextInt();

				boolean isValidAge = validate.validateAge(age);

				if (!isValidAge) {
					System.out.println("Wrong input ..try again");
					continue;
				}

				sc.nextLine();

				System.out.println("Enter Gender: ");
				String gender = sc.nextLine();

				boolean isValidGender = validate.validateGender(gender);

				if (!isValidGender) {
					System.out.println("Wrong input ..try again");
					continue;
				}

				System.out.println(
						"Enter your username: \n(Any capital or lower letter , (0-9) one number is mandatory , only _ special character is allowed, username lengeth should be (6-20)");
				String username = sc.nextLine();

				boolean isValidUsername = validate.validateUsername(username);

				if (!isValidUsername) {
					System.out.println("Wrong input ..try again");
					continue;
				}

				System.out.println(
						"Enter your password: \n(One capital and one lower letter mandatory, (0-9) one number is mandatory , @,#,$,% only this special character is allowed, username lengeth should be (6-20)");
				String password = sc.nextLine();

				boolean isValidPassword = validate.validatePassword(password);

				if (!isValidPassword) {
					System.out.println("Wrong input ..try again");
				}

				String acccountNumber = (int) (Math.random() * 123456) + "";

				List<String> transactions = new ArrayList();

				customerDetails.setAccountHolderName(accountHolderName);
				customerDetails.setPhoneNumber(phoneNumber);
				customerDetails.setEmail(email);
				customerDetails.setAge(age);
				customerDetails.setGender(gender);

				accountDetails.setAcccountNumber(acccountNumber);
				accountDetails.setBalance(initialBalance);

				loginDetails.setUsername(username);
				loginDetails.setPassword(password);

				transaction.setTransactions(transactions);

				loginDetails.setTransaction(transaction);
				accountDetails.setLoginDetails(loginDetails);
				customerDetails.setAccountDetails(accountDetails);

				if (isValidNumber && isValidAge && isValidGender) {
					result = service.addAccountDetails(customerDetails);
				}

				if (result == 1) {
					System.out.println("Account created");
					System.out.println("Your Account number is: " + acccountNumber);
				}

				else {
					try {
						throw new AccountNotCreatedException("Registration Failure...try again");
					}

					catch (AccountNotCreatedException e) {

						System.err.println(e);

					}
				}

				break;

			case 2:

				if (service.getLogin()) {
					outer: while (true) {

						System.out.println("1. Show Balance ");
						System.out.println("2. Deposit ");
						System.out.println("3. Withdraw ");
						System.out.println("4. Funds Transfer");
						System.out.println("5. Print Transactions");
						System.out.println("6. Logout");

						choice = sc.nextInt();

						sc.nextLine();

						switch (choice) {

						case 1:

							service.showBalance();

							break;

						case 2:

							System.out.println("Enter the fund you want to deposit");
							bal = sc.nextInt();

							flag = service.deposit(bal);

							if (flag == true) {

								System.out.println("Funds Deposited");
							}

							else {
								System.out.println("Wrong Information... try again");
							}

							break;

						case 3:

							System.out.println("Enter the fund you want to withdraw");
							bal = sc.nextInt();

							flag = service.withdraw(bal);

							if (flag == false) {
								System.out.println("Wrong Information .. try again");
							}

							break;

						case 4:

							System.out.println("Enter Reciever Account Number: ");
							String recieverAccountNumber = sc.nextLine();

							System.out.println("Enter the fund you want to transfer");
							bal = sc.nextInt();

							if (service.fundTransfer(recieverAccountNumber, bal)) {
								System.out.println("fund tranfer successful");
							} else {
								System.out.println("fund tranfer failed");
							}

							break;

						case 5:

							service.printTransactions();
							break;

						case 6:

							break outer;

						}

					}

				}

				else {
					System.out.println("Login failed");
				}

				break;

			case 3:

				System.exit(0);
			}
		}
	}
}
