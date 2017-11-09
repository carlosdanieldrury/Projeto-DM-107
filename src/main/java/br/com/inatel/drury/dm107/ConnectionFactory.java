package br.com.inatel.drury.dm107;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
	
	public Connection getConnection() throws Exception{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_dm107", "root", "");
	}

	
}
