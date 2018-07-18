package com.capgemini.wallet.service;

import com.capgemini.wallet.bean.CustomerDetails;
import com.capgemini.wallet.dao.CustomerDao;
import com.capgemini.wallet.dao.ICustomerDao;

public class CustomerService implements ICustomerService{
	
	ICustomerDao dao = new CustomerDao();
	
	public int addCustomerDetails(CustomerDetails customerDetails)
	{
		return dao.addCustomerDetails(customerDetails);
	}

}
