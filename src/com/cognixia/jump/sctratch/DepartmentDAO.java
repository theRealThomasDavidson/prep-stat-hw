package com.cognixia.jump.sctratch;

import java.util.List;

public interface DepartmentDAO {
	
	public List<Department> getAllDepartments();
	public Department getDepartmentById(int deptId);
	public Department getDepartmentByName(String deptName);
	
	public boolean addDepartment(Department dept);
	public boolean deleteDepartment(int deptId);
	public boolean updateDepartment(Department dept);
}
