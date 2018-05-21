package com.grabnotes.lib.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
	private static String connection_string = "jdbc:mysql://localhost:3306/mydb";
	private static String username = "root";
	private static String password = "Mounik10";
	private static Connection connection = null;

	public static Connection getConnection() throws Exception {
		if (connection == null) {
			synchronized (Connection.class) {
				if (connection == null) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						connection = DriverManager.getConnection(connection_string, username, password);
						System.out.println("DB connection with MYSQL established");

					} catch (Exception e) {
						System.out.println("problem with db");
						e.printStackTrace();
					}
				}
			}
		}
		return connection;
	}


}
