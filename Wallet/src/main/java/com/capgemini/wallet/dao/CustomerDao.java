package com.capgemini.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.wallet.bean.CustomerDetails;

public class CustomerDao {

	int status;

	public int addCustomerDetails(CustomerDetails customerDetails)

	{
		try {
			Connection con = DBConnection.getConnection();
			String Query = "insert into Customer(name, phoneNumber, email, gender, age, accountNumber) values(?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, customerDetails.getName());
			pstmt.setLong(2, customerDetails.getPhoneNumber());
			pstmt.setString(3, customerDetails.getEmail());
			pstmt.setString(4, customerDetails.getGender());
			pstmt.setInt(5, customerDetails.getAge());
			pstmt.setString(6, customerDetails.getAccountDetails().getAcccountNumber());

			status = pstmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

}
