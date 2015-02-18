package org.meruvian.workshop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DMLWithPreparedStatement {
	private String jdbcUri = "jdbc:mysql://localhost:3306/workshop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private Connection connection;
	
	public static void main(String[] args) throws Exception {
		DMLWithPreparedStatement main = new DMLWithPreparedStatement();
		main.openConnection();
		main.insertData();
		main.selectAll();
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
	
	private void insertData() throws Exception{
		String query = "INSERT INTO article (title, content) values (?, ?)";
		
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, "Helloo");
		stmt.setString(2, "Hello world!!");
		
		int result = stmt.executeUpdate();
		
		System.out.println(result + " rows inserted");
	}
	
	private void selectAll() throws Exception {
		String query = "SELECT a.id, a.title, a.content FROM article a WHERE title = ?";
		
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, "Helloo");
		
		ResultSet result = stmt.executeQuery();
		
		while (result.next()) {
			System.out.println("ID      : " + result.getInt(1));
			System.out.println("TITLE   : " + result.getString(2));
			System.out.println("CONTENT : " + result.getString(3));
			System.out.println();
		}
	}
}
