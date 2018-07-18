package com.capgemini.wallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.wallet.bean.CustomerDetails;

public class TransactionDao implements ITransactionDao {

	int status;
	static String accountNumber;
	Connection con;

	public int addTransactionDetails(CustomerDetails customerDetails)

	{
		try {

			con = DBConnection.getConnection();
			String Query = "insert into Transaction(transactions,accountNumber) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, "Transactions: ");
			pstmt.setString(2, customerDetails.getAccountDetails().getAcccountNumber());

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

	public void addTransactions(String transaction) {

		try {
			
			con = DBConnection.getConnection();
			String Query = "Update transaction SET transactions=CONCAT(transactions, ?) where accountNumber=?";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, "\n" + transaction);
			pstmt.setString(2, accountNumber);
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

	}

	public void printTransactions() {

		try {

			con = DBConnection.getConnection();
			String Query = "select * from transaction where accountNumber=?";
			PreparedStatement pstmt = con.prepareStatement(Query);
			pstmt.setString(1, accountNumber);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("transactions"));
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
	}

}
