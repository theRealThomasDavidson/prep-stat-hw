package com.cognixia.jump.sctratch;

public class RunnerDAO {
	
	public static void main(String[] args) {
		DepartmentDAO deptDAO = new DepartmentDAOClass();
		System.out.println("Here are all the departments: \n");
		for(Department dept : deptDAO.getAllDepartments()) {
			System.out.println(dept);
		}
		int id = 10008;
		System.out.println("\nHere is the Department record with the id: " + id + ": ");
		System.out.println(deptDAO.getDepartmentById(id));
		
		Department deptPhysics = new Department(10254, "Physics", "9876522289");
		System.out.println("\nAdding department Physics");
		if (deptDAO.addDepartment(deptPhysics)){
			System.out.println("Added Physics");
		}
		System.out.println("\nUpdating department Physics");
		deptPhysics = deptDAO.getDepartmentByName("Physics");
		deptPhysics.setPhone( "9876272489");
		if (deptDAO.updateDepartment(deptPhysics)){
			System.out.println("Updated Physics phone number");
		}
		System.out.println("\ndeleting department Physics");
		if (deptDAO.deleteDepartment(deptPhysics.getId())){
			System.out.println("deleting Physics");
		}
		
	}
}
