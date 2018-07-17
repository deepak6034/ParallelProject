package com.capgemini.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.wallet.bean.CustomerDetails;

public class AccountDao {

	int status;
	int bal;
	boolean flag = false;
	static String accountNumber;

	public int addAccountDetails(CustomerDetails customerDetails)

	{
		try {
			Connection con = DBConnection.getConnection();
			String Query = "insert into Account(accountHolderName, accountNumber, balance) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, customerDetails.getAccountDetails().getAccountHolderName());
			pstmt.setString(2, customerDetails.getAccountDetails().getAcccountNumber());
			pstmt.setInt(3, customerDetails.getAccountDetails().getBalance());

			status = pstmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

	public void showBalance() {

		try {

			Connection con = DBConnection.getConnection();
			String Query = "select balance from Account where accountNumber=?";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, accountNumber);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bal = rs.getInt("balance");
				System.out.println("Balance = " + bal);
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int deposit(int amount) {

		try {
			Connection con = DBConnection.getConnection();
			String Query = "update Account SET balance= balance+? where accountNumber=?";
			PreparedStatement pstmt = con.prepareStatement(Query);

			pstmt.setInt(1, amount);
			pstmt.setString(2, accountNumber);

			flag = false;

			status = pstmt.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public int withdraw(int amount) {

		try {
			Connection con = DBConnection.getConnection();
			String Query = "update Account SET balance= IF(balance>?, balance-?, balance) where accountNumber=?";
			PreparedStatement pstmt = con.prepareStatement(Query);

			pstmt.setInt(1, amount);
			pstmt.setInt(2, amount);
			pstmt.setString(3, accountNumber);

			showBalance();
			if (bal > amount)
				status = pstmt.executeUpdate();
			else
				status = 0;

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}
}
