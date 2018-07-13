package com.capgemini.wallet.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.wallet.bean.AccountDetails;
import com.capgemini.wallet.dao.AccountDao;

public class AccountValidation {

	private Pattern pattern;
	private Matcher matcher;
	private static final String username_Pattern = "((?=.*\\d)(?=.*[_@#$%]).{6,20})";
	private static final String password_Pattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[_@#$%]).{6,20})";

	public boolean validateUsername(String username) {
		
		boolean flag;
		pattern = Pattern.compile(username_Pattern);
		matcher = pattern.matcher(username);
		flag = matcher.matches();
		
		if(flag)
		{
			for(String uname : AccountDao.account.keySet())
			{
				if(username.equals(uname))
				{
					flag = false;
				}
			}
		}
		
		return flag;
	}

	public boolean validatePassword(String password) {
		pattern = Pattern.compile(password_Pattern);

		matcher = pattern.matcher(password);
		return matcher.matches();

	}

	public boolean validateEmail(String email) {

		pattern = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", pattern.CASE_INSENSITIVE);

		matcher = pattern.matcher(email);

		return matcher.matches();

	}

	public boolean validatePhoneNumber(String phoneNumber) {
		if (phoneNumber.length() == 10)
			return true;
		else
			return false;
	}

	public boolean validateAge(int age) {
		if (age > 15)
			return true;
		else
			return false;
	}

	public boolean validateGender(String gender) {
		if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("m")
				|| gender.equalsIgnoreCase("f"))
			return true;
		else
			return false;
	}

	public boolean validateLogin(String username, String password) {
		for (String uname : AccountDao.account.keySet()) {
			if (username.equals(uname)) {
				if (AccountDao.account.get(uname).getAccountDetails().getLoginDetails().getPassword().equals(password)) {
					return true;
				}
			}

		}
		return false;

	}

}
