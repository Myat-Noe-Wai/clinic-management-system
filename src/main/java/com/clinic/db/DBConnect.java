package com.clinic.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static Connection conn;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_db", "myatnoe", "aE3#H2jJ");
//			if(conn == null) {
//				System.out.println("Can't connect");
//			}else {
//				System.out.println("Connected db Successfully");
//			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return conn;
	}
}
