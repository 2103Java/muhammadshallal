package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionUtil {
	private static final String DB_URL = "jdbc:postgresql://ers.csdlvyc8g6b3.us-west-2.rds.amazonaws.com/ers";
	private static final String USER = "postgres";
	private static final String PASS = "password123";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		return DriverManager.getConnection(DB_URL,USER,PASS);
	}
}
