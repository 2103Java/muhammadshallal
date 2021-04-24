package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public final class ConnectionUtil {


	private static final String URL = System.getenv("ers_url");
	private static final String USERNAME = System.getenv("ers_username");
	private static final String PASSWORD = System.getenv("ers_password");
	
	private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Issues with establishing a connection.");
			e.printStackTrace();
		} 
		return connection;
	}
	
	
}
