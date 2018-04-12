/*
 * SQLPing.java
 *
 * Created on 12 November 2007, 19:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.nbh.research.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author neal
 */
public class SQLPing {
    /*
        <arg value="${driver}"/>
        <arg value="${url}"/>
        <arg value="${user}"/>
        <arg value="${pw}"/>
    */
    
	// look at build.xml...
	public static void main(String[] args) throws Exception{
		Class.forName(args[0]);
		String pw=args[3];
		System.out.println("1");
		if ("null".equals(pw)){
			pw="";
		}
		System.out.println("2: "+pw);
		Connection c = DriverManager.getConnection(args[1],args[2],pw);		
		Statement s = c.createStatement();
		s.execute("select 1 from 1");
		c.close();
		System.out.println("connected correctly");
	}

    
}
