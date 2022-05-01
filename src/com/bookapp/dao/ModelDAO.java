package com.bookapp.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ModelDAO {
	static Connection connection;

	public static Connection openConnection() {
		
		Properties properties = new Properties();
		
		try {
			properties.load(new FileReader("jdbc.properties"));

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String url = properties.getProperty("driver");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
