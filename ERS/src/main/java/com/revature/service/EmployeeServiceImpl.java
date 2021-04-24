package com.revature.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.util.ConnectionUtil;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public int getEmployeeCount() {
		// TODO Auto-generated method stub
		Connection connection;
		try {
			connection = ConnectionUtil.getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT count(*) from employee where manager = false";
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			return rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

}
