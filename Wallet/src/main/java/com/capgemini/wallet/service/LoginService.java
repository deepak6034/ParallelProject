package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;
import com.capgemini.wallet.dao.ILoginDao;
import com.capgemini.wallet.dao.LoginDao;

public class LoginService implements ILoginService {

	ILoginDao dao = new LoginDao();

	public boolean getLogin() {
		return dao.getLogin();
	}

	public int addLoginDetails(CustomerDetails customerDetails) {
		return dao.addLoginDetails(customerDetails);
	}

	public boolean authenticate(String username, String password) {
		return dao.authenticate(username, password);
	}
}
