package io.mod2.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	
	private static final String DB_URL="jdbc:mysql://localhost:3306/rest?autoReconnect=true&useSSL=false";
	private static final String DB_User="root";
	private static final String DB_Password="root";
	
	
	static{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver Loaded");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC Driver Failed to Load");
			e.printStackTrace();
			
		}
	}
	
	public static Connection connect(){
		
		Connection con=null;
		
		
		try {
			con=DriverManager.getConnection(DB_URL, DB_User, DB_Password);
		} catch (SQLException e) {
			System.err.println("Error getting connection");
			e.printStackTrace();
		}
		return con;
	}

}
