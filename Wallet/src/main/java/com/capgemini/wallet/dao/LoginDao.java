package com.capgemini.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.wallet.bean.CustomerDetails;

public class LoginDao {

	int status;
	boolean flag = false;
	
	public int addLoginDetails(CustomerDetails customerDetails)

	{
		try {
			Connection con = DBConnection.getConnection();
			String Query = "insert into Login(username, password, accountNumber) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, customerDetails.getAccountDetails().getLoginDetails().getUsername());
			pstmt.setString(2, customerDetails.getAccountDetails().getLoginDetails().getPassword());
			pstmt.setString(3, customerDetails.getAccountDetails().getAcccountNumber());
			
			status = pstmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}
	
	public boolean authenticate(String username, String password)
	{
		try {
			Connection con = DBConnection.getConnection();
			String Query = "select accountNumber from Login where username=? and password=?";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				flag=true;
				AccountDao.accountNumber = rs.getString("accountNumber");
			
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
		
	}

}
