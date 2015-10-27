package com.nbh.common;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class TestDb  {

	public static final String dbURL = "jdbc:db2:CO001DDA";
	//public static final String dbURL = "jdbc:hsqldb:hsql://localhost";

	public static void main(String[] args){
		Connection conn=null;

		Properties properties = new Properties(); // Create Properties object
		//properties.put("user", "db2");         // Set user ID for connection
		//properties.put("password", "honda");     // Set password for connection
		properties.put("user", "sa");         // Set user ID for connection
		properties.put("password", "");     // Set password for connection


		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Class.forName("org.hsqldb.jdbcDriver");

			DriverManager.setLogWriter(new PrintWriter(System.out));

			conn = DriverManager.getConnection(dbURL, properties);
			System.out.println("Autocommit? "+conn.getAutoCommit());
		}
		catch(SQLException sql) {
			System.out.println("SQLException: " + sql.getMessage());
			System.out.println("cause: "+sql.getCause());
			System.out.println("sql state: "+sql.getSQLState());
			System.out.println("sql code: "+sql.getErrorCode());
			sql.printStackTrace();
			sql = sql.getNextException();
			if (sql!=null){
				System.out.println("SQLException: " + sql.getMessage());
			}
		}
		catch(ClassNotFoundException cnfe) {
			System.out.println("ClassNotFoundException: " + cnfe.getMessage());
		}finally{
			if (conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
				}
			}
			System.out.println("done");
		}
	}
}

