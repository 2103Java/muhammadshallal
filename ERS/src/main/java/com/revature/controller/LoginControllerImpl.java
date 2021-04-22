package com.revature.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class LoginControllerImpl implements LoginController {

	@Override
	public Employee authenticate(String email, String password) {
		// TODO Auto-generated method stub
		try {
			String hashPass = hashPass(password);
			Connection connection = ConnectionUtil.getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * from employee where email = '"+ email +"' and password = '"+ hashPass +"'";
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next())
				return new Employee(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),
					rs.getString("ssn"), rs.getString("address"), rs.getBoolean("manager"), 
					rs.getString("phonenumber"), rs.getString("email"), rs.getString("password"));
			else throw new SQLException();
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	protected String hashPass(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = new byte[16];
		KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = f.generateSecret(spec).getEncoded();
		Base64.Encoder enc = Base64.getEncoder();
		return enc.encodeToString(hash);
	}

	
	
}
