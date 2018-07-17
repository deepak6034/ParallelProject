package com.capgemini.wallet.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capgemini.wallet.bean.CustomerDetails;

public class TransactionDao {
	

	int status;
	public int addTransactionDetails(CustomerDetails customerDetails)

	{
		try {
			Connection con = DBConnection.getConnection();
			String Query = "insert into Transaction(transactions, accountNumber) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement("Query");
			pstmt.setBlob(1, (Blob) customerDetails.getAccountDetails().getLoginDetails().getTransaction().getTransactions());
			pstmt.setString(2, customerDetails.getAccountDetails().getAcccountNumber());
			
			status = pstmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

}
