package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConection {
	
	private static DatabaseConection databaseConection;
	private Connection connection;
	
	private DatabaseConection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/"+
					"loja?user=root&useSSL=false"
					);
						
		} catch (Exception e) {
			System.out.println("Não foi possível conectar no banco de dados.");
		}
	}
	
	public static DatabaseConection getInstance() {
		if (databaseConection == null) databaseConection = new DatabaseConection();		
		return databaseConection;
	}
	
	public Connection getConnection() {
		
		return connection;
	}

}
