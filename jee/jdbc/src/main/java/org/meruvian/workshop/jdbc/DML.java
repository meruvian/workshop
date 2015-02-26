package org.meruvian.workshop.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DML {
	private String jdbcUri = "jdbc:mysql://localhost:3306/workshop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private Connection connection;
	
	public static void main(String[] args) throws Exception {
		DML main = new DML();
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
		String query = "INSERT INTO article (title, content) values ('Hello', 'Hello world!')";
		
		Statement stmt = connection.createStatement();
		int result = stmt.executeUpdate(query);
		
		System.out.println(result + " rows inserted");
	}
	
	private void selectAll() throws Exception {
		String query = "SELECT a.id, a.title, a.content FROM article a";
		
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while (result.next()) {
			System.out.println("ID      : " + result.getInt(1));
			System.out.println("TITLE   : " + result.getString(2));
			System.out.println("CONTENT : " + result.getString(3));
			System.out.println();
		}
	}
}
