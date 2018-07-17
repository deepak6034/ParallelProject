package com.capgemini.wallet.exception;

import java.util.Arrays;

public class AccountNotCreatedException extends Exception {

	private String msg;
	
	public AccountNotCreatedException(String msg)
	{
		this.msg = msg;
	}

	@Override
	public String toString() {
		return msg;
	}



}
