package com.capgemini.wallet.bean;

public class AccountDetails {

	private String acccountNumber;
	private int balance;
	Login loginDetails;



	public Login getLoginDetails() {
		return loginDetails;
	}

	public void setLoginDetails(Login loginDetails) {
		this.loginDetails = loginDetails;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getAcccountNumber() {
		return acccountNumber;
	}

	public void setAcccountNumber(String acccountNumber) {
		this.acccountNumber = acccountNumber;
	}

}
