package org.meruvian.workshop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DDL {
	private String jdbcUri = "jdbc:mysql://localhost:3306/workshop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private Connection connection;

	public static void main(String[] args) throws Exception {
		DDL main = new DDL();
		main.openConnection();
		main.createTable();
		main.closeConnection();
	}

	private void openConnection() throws Exception{
		// Call JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");

		// Open connection
		this.connection = DriverManager.getConnection(jdbcUri, jdbcUsername, jdbcPassword);
	}
	
	private void closeConnection() throws Exception {
		connection.close();
	}

	private void createTable() throws Exception {
		String query = "CREATE TABLE article (id INT(5) AUTO_INCREMENT PRIMARY KEY, "
				+ "title VARCHAR(30) NOT NULL, content LONGTEXT)";
		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(query);
		
		System.out.println("Table created");
	}
}
