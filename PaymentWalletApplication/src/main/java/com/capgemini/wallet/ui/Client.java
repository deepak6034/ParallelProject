package com.capgemini.wallet.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.dao.AccountServiceDao;
import com.capgemini.wallet.exception.AccountNotCreatedException;
import com.capgemini.wallet.service.AccountDataValidation;

public class Client {

	public static void main(String[] args) {

		int bal;
		String Username;
		String Password;
		String accNo;
		boolean flag;
		int result = 0;
		Scanner sc = new Scanner(System.in);
		AccountServiceDao service = new AccountServiceDao();
		AccountDataValidation validate = new AccountDataValidation();

		while (true) {

			AccountDetails details = new AccountDetails();

			System.out.println("\nWelcome to Technofitz Payment Wallet Application \n");
			System.out.println("1. Create Account: ");
			System.out.println("2. Show Balance ");
			System.out.println("3. Deposit ");
			System.out.println("4. Withdraw ");
			System.out.println("5. Funds Transfer");
			System.out.println("6. Print Transactions");

			int choice = sc.nextInt();

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

				System.out.println("Enter your username: (Any capital or lower letter , (0-9) one number is mandatory , \n_ only this special character is allowed, username lengeth should be (6-20)");
				String username = sc.nextLine();

				boolean isValidUsername = validate.validateUsername(username);

				if (!isValidUsername) {
					System.out.println("Wrong input ..try again");
					continue;
				}

				System.out.println("Enter your password: (One capital and one lower letter mandatory, (0-9) one number is mandatory , \n@,#,$,% only this special character is allowed, username lengeth should be (6-20)");
				String password = sc.nextLine();

				boolean isValidPassword = validate.validatePassword(password);

				if (!isValidPassword) {
					System.out.println("Wrong input ..try again");
				}

				String acccountNumber = (int) (Math.random() * 123456) + "";

				List<String> transactions = new ArrayList();

				details.setAccountHolderName(accountHolderName);
				details.setPhoneNumber(phoneNumber);
				details.setEmail(email);
				details.setAge(age);
				details.setGender(gender);
				details.setUsername(username);
				details.setPassword(password);
				details.setAcccountNumber(acccountNumber);
				details.setTransactions(transactions);

				if (isValidNumber && isValidAge && isValidGender) {
					result = service.addAccountDetails(details);
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
				
				System.out.println("Enter your username: ");
				Username = sc.nextLine();

				System.out.println("Enter your password: ");
				Password = sc.nextLine();

				System.out.println("Enter the Account Number: ");
				accNo = sc.nextLine();

				flag = service.showBalance(Username, Password, accNo);

				if (flag == false) {
					System.out.println("Wrong Information...try again");
				}

				break;

			case 3:

				System.out.println("Enter your username: ");
				Username = sc.nextLine();

				System.out.println("Enter your password: ");
				Password = sc.nextLine();

				System.out.println("Enter the Account Number: ");
				accNo = sc.nextLine();

				System.out.println("Enter the fund you want to deposit");
				bal = sc.nextInt();

				flag = service.deposit(Username, Password, accNo, bal);

				if (flag == true) {
					System.out.println("Funds Deposited");
				}

				else {
					System.out.println("Wrong Information... try again");
				}

				break;

			case 4:

				System.out.println("Enter your username: ");
				Username = sc.nextLine();

				System.out.println("Enter your password: ");
				Password = sc.nextLine();

				System.out.println("Enter the Account Number: ");
				accNo = sc.nextLine();

				System.out.println("Enter the fund you want to withdraw");
				bal = sc.nextInt();

				flag = service.withdraw(Username, Password, accNo, bal);

				if (flag == false) {
					System.out.println("Wrong Information .. try again");
				}

				break;

			case 5:

				System.out.println("Enter your username: ");
				Username = sc.nextLine();

				System.out.println("Enter your password: ");
				Password = sc.nextLine();

				System.out.println("Enter your Account Number: ");
				String SenderAccountNumber = sc.nextLine();

				System.out.println("Enter Reciever Account Number: ");
				String RecieverAccountNumber = sc.nextLine();

				System.out.println("Enter the fund you want to transfer");
				bal = sc.nextInt();

				if (service.fundTranfer(Username, Password, SenderAccountNumber, RecieverAccountNumber, bal)) {
					System.out.println("fund tranfer successful");
				} else {
					System.out.println("fund tranfer failed");
				}

				break;

			case 6:

				System.out.println("Enter your username: ");
				Username = sc.nextLine();

				System.out.println("Enter your password: ");
				Password = sc.nextLine();

				System.out.println("Enter the Account Number: ");
				accNo = sc.nextLine();

				flag = service.printTransactions(Username, Password, accNo);

				if (flag == false) {
					System.out.println("Wrong Information .. try again");
				}

			}

		}

	}
}
