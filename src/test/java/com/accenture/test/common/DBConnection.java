package com.accenture.test.common;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


/**
 ****************************************************************************
 * HIGHLIGHTS:
 * > Base methods to perform a query to the data base.
 * Step 1 - Open Connection
 * Step 2 - Execute the query
 * Step 3 - Close the connection
 * Data: Information needed is: host, port, service name, username, password and query.
 * 		 This data can be set in a data class that keeps different values using switch 
 ****************************************************************************
 */

public class DBConnection {

	/**
	 * OBJECTIVE: Step 1 to perform a query on DB - Open Connection.
	 */	
	public static Connection openConnection (String host, String port, String serviceName, String username, String password) throws SQLException, ClassNotFoundException{

		Class.forName("oracle.jdbc.driver.OracleDriver");  

		Connection con=DriverManager.getConnection("jdbc:oracle:thin:" + username +"/" + password +"@" + host + ":"+ port + "/" + serviceName);
		
		return con;		
		
	}
	
	/**
	 * OBJECTIVE: Step 2 execute the query statement in the DB.
	 */	
	public static ResultSet executeQuery (Connection con, String queryStmt) throws SQLException {

		Statement stmt=con.createStatement();  

		ResultSet rs=stmt.executeQuery(queryStmt);
		
		return rs; 
		
	}
	
	/**
	 * OBJECTIVE: Step 3 After the Result Set is used then close connection.
	 */	
	public static void closeConnection (Connection con) throws SQLException {

		con.close(); 
		
	}	
	
}
