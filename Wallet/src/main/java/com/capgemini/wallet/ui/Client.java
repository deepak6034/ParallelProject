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
import com.capgemini.wallet.service.CustomerService;
import com.capgemini.wallet.service.IAccountService;
import com.capgemini.wallet.service.AccountService;
import com.capgemini.wallet.service.ICustomerService;
import com.capgemini.wallet.service.ILoginService;
import com.capgemini.wallet.service.ITransactionService;
import com.capgemini.wallet.service.LoginService;
import com.capgemini.wallet.service.TransactionService;

public class Client {

	static ICustomerService customerService = new CustomerService();
	static IAccountService accountService = new AccountService();
	static ILoginService loginService = new LoginService();
	static ITransactionService transactionService = new TransactionService();
	static AccountValidation validate = new AccountValidation();

	static CustomerDetails customerDetails = new CustomerDetails();
	static AccountDetails accountDetails = new AccountDetails();
	static Login loginDetails = new Login();
	static Transaction transaction = new Transaction();

	static int amount;
	static int choice;
	static int flag;
	int result = 0;
	static int initialBalance = 500;
	static String trans;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {

			System.out.println("\n     Welcome to Technofitz Payment Wallet Application \n");
			System.out.println("1. Create Account: ");
			System.out.println("2. Login ");
			System.out.println("3. Exit ");

			choice = sc.nextInt();

			sc.nextLine();

			switch (choice) {

			case 1:

				registration();

				break;

			case 2:

				login();

				break;

			case 3:

				System.exit(0);
			}
		}
	}

	public static boolean registration() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Name of the Account Holder: ");
		String accountHolderName = sc.nextLine();

		System.out.println("Enter Phone Number: ");
		long phoneNumber = sc.nextLong();

		boolean isValidNumber = validate.validatePhoneNumber(phoneNumber);

		if (!isValidNumber) {
			System.out.println("Wrong input ..try again");
			return false;
		}

		sc.nextLine();
		System.out.println("Enter Email: ");
		String email = sc.nextLine();

		boolean isValidateEmail = validate.validateEmail(email);

		if (!isValidateEmail) {
			System.out.println("Wrong input ..try again");
			return false;
		}

		System.out.println("Enter Age: ");
		int age = sc.nextInt();

		boolean isValidAge = validate.validateAge(age);

		if (!isValidAge) {
			System.out.println("Wrong input ..try again");
			return false;
		}

		sc.nextLine();

		System.out.println("Enter Gender: ");
		String gender = sc.nextLine();

		boolean isValidGender = validate.validateGender(gender);

		if (!isValidGender) {
			System.out.println("Wrong input ..try again");
			return false;
		}

		System.out.println(
				"Enter your username: \n(Any capital or lower letter , (0-9) one number is mandatory , only _ special character is allowed, username lengeth should be (6-20)");
		String username = sc.nextLine();

		boolean isValidUsername = validate.validateUsername(username);

		if (!isValidUsername) {
			System.out.println("Wrong input ..try again");
			return false;
		}

		System.out.println(
				"Enter your password: \n(One capital and one lower letter mandatory, (0-9) one number is mandatory , @,#,$,% only this special character is allowed, username lengeth should be (6-20)");
		String password = sc.nextLine();

		boolean isValidPassword = validate.validatePassword(password);

		if (!isValidPassword) {
			System.out.println("Wrong input ..try again");
			return false;
		}

		String acccountNumber = (int) (Math.random() * 123456) + "";

		List<String> transactions = new ArrayList();

		customerDetails.setName(accountHolderName);
		customerDetails.setPhoneNumber(phoneNumber);
		customerDetails.setEmail(email);
		customerDetails.setAge(age);
		customerDetails.setGender(gender);

		accountDetails.setAccountHolderName(accountHolderName);
		accountDetails.setAcccountNumber(acccountNumber);
		accountDetails.setBalance(initialBalance);

		loginDetails.setUsername(username);
		loginDetails.setPassword(password);

		transaction.setTransactions(transactions);

		loginDetails.setTransaction(transaction);
		accountDetails.setLoginDetails(loginDetails);
		customerDetails.setAccountDetails(accountDetails);

		if (customerService.addCustomerDetails(customerDetails) == 1
				&& accountService.addAccountDetails(customerDetails) == 1
				&& loginService.addLoginDetails(customerDetails) == 1
				&& transactionService.addTransactionDetails(customerDetails) == 1) {
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
		return true;
	}

	private static void login() {

		if (loginService.getLogin()) {
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

					System.out.println("Balance: " + accountService.showBalance());

					break;

				case 2:

					System.out.println("Enter the fund you want to deposit");
					amount = sc.nextInt();

					flag = accountService.deposit(amount);

					if (flag == 1) {

						System.out.println("Funds Deposited");
						trans = amount + " credited";
						transactionService.addTransactions(trans);

					}

					else {
						System.out.println("Wrong Information... try again");
					}

					break;

				case 3:

					System.out.println("Enter the fund you want to withdraw");
					amount = sc.nextInt();

					flag = accountService.withdraw(amount);

					if (flag == 1) {
						System.out.println("Withdrawal Done");
						trans = amount + " debited";
						transactionService.addTransactions(trans);
					} else {
						System.out.println("Insufficient Funds");
					}

					break;

				case 4:

					System.out.println("Enter Reciever Account Number: ");
					String recieverAccountNumber = sc.nextLine();

					System.out.println("Enter the fund you want to transfer");
					amount = sc.nextInt();
					flag = accountService.fundTransfer(recieverAccountNumber, amount);
					if (flag == 1) {
						System.out.println("fund tranfer successful");
						trans = amount + " transferrred to account number: " + recieverAccountNumber;
						transactionService.addTransactions(trans);
						
					} else {
						System.out.println("fund tranfer failed");
					}

					break;

				case 5:

					transactionService.printTransactions();
					break;

				case 6:

					break outer;

				}

			}

		}

		else {
			System.out.println("Login failed");
		}

	}

}
