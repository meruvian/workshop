package org.meruvian.workshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DML {
	private String jdbcUri = "jdbc:mysql://localhost:3306/workshop";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) throws Exception {
		DML main = new DML();
		main.openConnection();
		main.insertData();
		main.selectAll();
	}

	private void openConnection() throws Exception{
		DataSource dataSource = new DriverManagerDataSource(jdbcUri, jdbcUsername, jdbcPassword);
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private void insertData() throws Exception{
		String query = "INSERT INTO article2 (title, content) values ('Hello', 'Hello world!')";
		
		int result = jdbcTemplate.update(query);
		
		System.out.println(result + " rows inserted");
	}
	
	private void selectAll() throws Exception {
		String query = "SELECT a.id, a.title, a.content FROM article2 a";
		
		List<Object[]> result = jdbcTemplate.query(query, new RowMapper<Object[]>() {
			@Override
			public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3) };
			}
		});
		
		for (Object[] o : result) {
			System.out.println("ID      : " + o[0]);
			System.out.println("TITLE   : " + o[1]);
			System.out.println("CONTENT : " + o[2]);
			System.out.println();
		}
	}
}
