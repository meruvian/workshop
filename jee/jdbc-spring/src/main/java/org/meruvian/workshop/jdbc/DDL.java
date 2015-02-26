package org.meruvian.workshop.jdbc;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DDL {
	private String jdbcUri = "jdbc:mysql://localhost:3306/workshop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws Exception {
		DDL main = new DDL();
		main.openConnection();
		main.createTable();
	}

	private void openConnection() throws Exception{
		DataSource dataSource = new DriverManagerDataSource(jdbcUri, jdbcUsername, jdbcPassword);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private void createTable() throws Exception {
		String query = "CREATE TABLE article2 (id INT(5) AUTO_INCREMENT PRIMARY KEY, "
				+ "title VARCHAR(30) NOT NULL, content LONGTEXT)";
		jdbcTemplate.execute(query);
		
		System.out.println("Table created");
	}
}
