package com.capgemini.wallet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static Connection con;

	public static Connection getConnection() throws SQLException 
	{
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/wallet","root","Capgemini123");		
		} 
	
		
		catch (SQLException e) {
			e.printStackTrace();
		}
			
		return con;
		
		
	}
	
}
