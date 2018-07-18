package com.capgemini.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.wallet.bean.CustomerDetails;

public class AccountDao implements IAccountDao {

	int status;
	int bal;
	boolean flag = false;
	static String accountNumber;
	Connection con;

	public int addAccountDetails(CustomerDetails customerDetails)

	{
		try {
			
			con = DBConnection.getConnection();
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
		
		finally {

			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return status;

	}

	public int showBalance() {

		try {

			con = DBConnection.getConnection();
			String Query = "select balance from Account where accountNumber=?";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, accountNumber);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bal = rs.getInt("balance");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {

			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return bal;
	}

	public int deposit(int amount) {

		try {
			
			con = DBConnection.getConnection();
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
		
		finally {

			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return status;
	}

	public int withdraw(int amount) {

		try {
			
			con = DBConnection.getConnection();
			if (showBalance() >= amount) {
				String Query = "update Account SET balance = balance-? where accountNumber=?";
				PreparedStatement pstmt = con.prepareStatement(Query);

				pstmt.setInt(1, amount);
				pstmt.setString(2, accountNumber);

				status = pstmt.executeUpdate();
				System.out.println("Remaining balance: " + showBalance());
			}

			else {
				status = 0;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {

			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return status;

	}

	public int fundTransfer(String recieverAccountNumber, int amount) {

		try {

			con = DBConnection.getConnection();
			if (showBalance() >= amount) {
				String recieverQuery = "select * from Account where accountNumber=?";
				String SenderQuery = "update Account SET balance = balance-? where accountNumber=?";
				PreparedStatement recieverPstmt = con.prepareStatement(recieverQuery);
				PreparedStatement senderPstmt = con.prepareStatement(SenderQuery);

				recieverPstmt.setString(1, recieverAccountNumber);

				senderPstmt.setInt(1, amount);
				senderPstmt.setString(2, accountNumber);

				ResultSet rs = recieverPstmt.executeQuery();
				while (rs.next()) {
					flag = true;

					String recieverQuery1 = "update Account SET balance=balance+? where accountNumber=?";
					PreparedStatement recieverPstmt1 = con.prepareStatement(recieverQuery1);
					recieverPstmt1.setInt(1, amount);
					recieverPstmt1.setString(2, recieverAccountNumber);
					recieverPstmt1.executeUpdate();

					senderPstmt.executeUpdate();

				}

			}

			else {
				status = 0;
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {

			try {
				con.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		

		if (!flag) {
			System.out.println("Wrong Reciever account number");
		}
		return status;
	}
	
	
}
