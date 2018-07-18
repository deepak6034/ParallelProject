package com.capgemini.wallet.dao;

import com.capgemini.wallet.bean.CustomerDetails;

public interface ILoginDao {

	public boolean getLogin();
	public int addLoginDetails(CustomerDetails customerDetails);
	public boolean authenticate(String username, String password);
	
}
