package com.cognixia.jump.sctratch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOClass implements DepartmentDAO{
	
	private Connection conn = ConnManagerWithProperties.getConnection();
	
	@Override
	public List<Department> getAllDepartments() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM department");
			List<Department> deptList = new ArrayList<Department>();
			while(rs.next()) {
				int id = rs.getInt("dept_id");
				String name = rs.getString("dept_name");
				String phone = rs.getString("dept_phone");
				Department tempDept = new Department(id, name, phone);
				deptList.add(tempDept);
				
			}
			return deptList;
		}
		catch(SQLException e) {
			System.out.println("Could not retrieve list of departments.");
		}
		
		return null;
	}

	@Override
	public Department getDepartmentById(int deptId) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from department where dept_id = ?");
			pstmt.setInt(1,deptId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("dept_id");
				String name = rs.getString("dept_name");
				String phone = rs.getString("dept_phone");
				Department dept = new Department(id, name, phone);
				return dept;
			}
			
		}
		catch(SQLException e){
			System.out.println("Could not find a department with id: "+deptId);
		}
		return null;
	}

	@Override
	public Department getDepartmentByName(String deptName) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from department where dept_name like ?");
			pstmt.setString(1,deptName);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("dept_id");
				String name = rs.getString("dept_name");
				String phone = rs.getString("dept_phone");
				Department dept = new Department(id, name, phone);
				return dept;
			}
			
		}
		catch(SQLException e){
			System.out.println("Could not find a department with name: "+deptName);
		}
		return null;
	}

	@Override
	public boolean addDepartment(Department dept) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into department"
														+ "(dept_id, dept_name, dept_phone) "
														+ "values (?, ?, ?)");
			pstmt.setInt(1, dept.getId());
			pstmt.setString(2,  dept.getName());
			pstmt.setString(3, dept.getPhone());
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}
		}
		catch(SQLException e){
			System.out.println("Could not add department");
		}
		return false;
	}

	@Override
	public boolean deleteDepartment(int deptId) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from department where dept_id = ?");
			pstmt.setInt(1, deptId);
			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}
		}
		catch(SQLException e) {
			System.out.println("Could not find department with id: "+ deptId);
		}
		return false;
	}

	@Override
	public boolean updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstmt = conn.prepareStatement("update department set dept_name = ?, dept_phone = ? where dept_id = ?");
			pstmt.setString(1,  dept.getName());
			pstmt.setString(2, dept.getPhone());
			pstmt.setInt(3, dept.getId());
			int count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}
		}
		catch(SQLException e) {
			System.out.println("Could not find department with id: "+ dept.getId());
		}
		return false;
	}

}
