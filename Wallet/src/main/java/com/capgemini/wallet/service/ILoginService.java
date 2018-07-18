package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;

public interface ILoginService {
	
	public boolean getLogin();
	public int addLoginDetails(CustomerDetails customerDetails);
	public boolean authenticate(String username, String password);
}
