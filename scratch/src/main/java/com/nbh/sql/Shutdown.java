package com.nbh.sql;

import java.sql.*;

public class Shutdown{

	public static void main(String[] args) throws Exception{
		Class.forName("org.hsqldb.jdbcDriver");
		Connection c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/","sa","");
		Statement s = c.createStatement();
		s.execute("SHUTDOWN");
		c.close();
		System.out.println("HSQL closed correctly");
	}
}