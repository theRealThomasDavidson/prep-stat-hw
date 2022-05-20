package com.cognixia.jump.sctratch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//make this so it can take an input to display all people in any year.
public class PreparedStatementExample {
	
	public static void main(String[] args) {
		try {
			
			Map <Integer, String> className = new HashMap <Integer, String>();
			className.put(0,"Freshman");
			className.put(1,"Sophmore");
			className.put(2,"Junior");
			className.put(3,"Senior");
			Map<Integer, Integer[]> classRange = new HashMap<Integer, Integer[]>();			
			classRange.put(0,new Integer[] {0,30});
			classRange.put(1,new Integer[] {31,60});
			classRange.put(2,new Integer[] {61,90});
			classRange.put(3,new Integer[] {91,Integer.MAX_VALUE});
			Connection conn = ConnManagerWithProperties.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(
					"select * from student where credits >=? and credits <=?");
			Scanner input = new Scanner(System.in);
			int credits;
			String year;
			System.out.println("Please select the number of a class from the list below to see every memeber of that class.");
			for(int ndx = 0; ndx < 4; ndx+=1) {
				System.out.println(""+ ndx + ": "+ className.get(ndx));
			}
			int legal[] = { 0,1,2,3 };
			int nput;
			
			do {
				System.out.println("Please select a number from the list above");
				nput = input.nextInt();
				input.nextLine();
			}
			while ( Arrays.asList(legal).contains(nput) );
			
			try {
				pstmt.setInt(1, classRange.get(nput)[0]);
				pstmt.setInt(2, classRange.get(nput)[1]);
				ResultSet rs = pstmt.executeQuery();
				System.out.println("\n\nMemebers of " + className.get(nput));
				System.out.println("range of :" + classRange.get(nput)[0] + "-" + classRange.get(nput)[1] + " credits.");
				while(rs.next()){
					credits = rs.getInt("credits");
					year = className.get(Math.min(Math.max((credits-1)/30, 0), 4));
					System.out.println("\nID:     \t" +rs.getInt("student_id"));
					System.out.println("name:   \t" +rs.getString("first_name") + " " + rs.getString("last_name"));
					System.out.println("credits:\t" +credits);
				}
				rs.close();	
			}
			catch(SQLException e) {
				System.out.println("Something went wrong.");
			}
			
			pstmt.close();
			pstmt = conn.prepareStatement(
					"select count(*) as c from student where credits >=? and credits <=?");

			System.out.println("Please select the number of a class from the list below to see every memeber of that class.");
			for(int ndx = 0; ndx < 4; ndx+=1) {
				System.out.println(""+ ndx + ": "+ className.get(ndx));
			}
			do {
				System.out.println("Please select a number from the list above");
				nput = input.nextInt();
				input.nextLine();
			}
			while ( Arrays.asList(legal).contains(nput) );
			try {
				pstmt.setInt(1, classRange.get(nput)[0]);
				pstmt.setInt(2, classRange.get(nput)[1]);
				ResultSet rs = pstmt.executeQuery();
				System.out.println("\n\nMemebers of " + className.get(nput));
				System.out.println("range of :" + classRange.get(nput)[0] + "-" + classRange.get(nput)[1] + " credits.");
				while(rs.next()){
					System.out.println("count:\t" + rs.getInt("c"));
				}
				rs.close();	
			}
			catch(SQLException e) {
				System.out.println("Something went wrong.");
			}
			
			pstmt.close();
			conn.close();
		}
		catch( SQLException e) {
			System.out.println("something was missed");
		}

	}

}
