package com.cognixia.jump.sctratch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognixia.jump.sctratch.ConnManagerWithProperties;

public class ResultSetMetaDataExample {

	public static void main(String[] args) {
		
		try {
			
			// set up connection, statement, and resultset
			Connection conn = ConnManagerWithProperties.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student");
			
			// create an meta data object that will hold meta data about the result
			// set we have
			ResultSetMetaData rsMetadata = rs.getMetaData();
			
			// here we can get info about the columns from our result set
			// we can iterate through these columns and get info on each of them
			for(int col = 1; col <= rsMetadata.getColumnCount(); col++) {
				
				String name = rsMetadata.getColumnName(col); 		 // name of column
				int type = rsMetadata.getColumnType(col);			 // numeric value of its data type
				String typeName = rsMetadata.getColumnTypeName(col); // string describing data type
				
				System.out.println(col + ": Name = " + name + ", Type Number = " + type 
						+ ", Type Name = " + typeName);
				
			}
			
			// close connections
			rs.close();
			stmt.close();
			conn.close();			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

}