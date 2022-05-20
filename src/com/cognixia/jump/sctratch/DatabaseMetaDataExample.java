package com.cognixia.jump.sctratch;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.sctratch.ConnManagerWithProperties;

public class DatabaseMetaDataExample {
	
	public static void main(String[] args) {
		
		try {

			Connection conn = ConnManagerWithProperties.getConnection();
			
			// create metadata object for our connection
			DatabaseMetaData dbmd = conn.getMetaData();
			
			///////////////////
			// DATABASE INFO //
			///////////////////
			
			// we can retrieve info for a specific database and store it in a ResultSet object
			// even if we connect to the sakila database, can still access info for our
			// university database
			// reference slide 47 to get more info on each of the arguments in this method
			ResultSet rs = dbmd.getTables("university", "public", "%", new String[] { "TABLE" });
			
			// go to first row
			rs.first();
			
			// print all the tables in university database
			System.out.println("\nTables");
			System.out.println("---------------------------------");
			do {
				System.out.println(rs.getString("TABLE_NAME"));
				
			} while(rs.next());
			
			System.out.println("\n\n\n");
			
			/////////////////////
			// CONNECTION INFO //
			/////////////////////
			
			// many different getters that return info on our connection
			System.out.println("Driver Name: " + dbmd.getDriverName());
			System.out.println("Driver Version: " + dbmd.getDriverVersion());
			System.out.println("Username: " + dbmd.getUserName());
			
			
			// the getCatalogs can be used to get all the databases on our connection
			rs = dbmd.getCatalogs();
			rs.first();
			
			System.out.println("\n\nDATABASES");
			System.out.println("--------------------------------");
			do {
				System.out.println(rs.getString(1));
				
			} while(rs.next());
			
			
			rs.close();
			conn.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}