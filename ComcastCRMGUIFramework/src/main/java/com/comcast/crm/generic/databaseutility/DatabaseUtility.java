package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
	
public class DatabaseUtility {
	
	Connection con;
	public void getDBconnection(String url, String username, String password) throws SQLException {
		try {
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		Connection con = DriverManager.getConnection(url, username, password);
	} catch (Exception e) {
	}
	}
		
		public void closeConnection() throws SQLException {
			con.close();
		}
		
		public ResultSet executeSelectQuery(String query) throws SQLException {
			ResultSet result= null;
			Statement stat = con.createStatement();
		 result=stat.executeQuery(query);
		 return result;
		}
		
		public int executeNonSelectQuery(String query) throws SQLException {
			Statement stat = con.createStatement();
		int result=stat.executeUpdate(query);
		 return result;
			
		}
	
	public void getDBconnected() throws SQLException {
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydictinory", "root", "root");
		con.close();
}
}
