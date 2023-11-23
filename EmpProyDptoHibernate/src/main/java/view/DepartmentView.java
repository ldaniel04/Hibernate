package view;

import java.util.List;

import exceptions.OurExceptions;
import library.IO;
import models.Department;
import models.Worker;

public class DepartmentView {

	final String options = """	

			1. Create a Department
			2. Show Departments
			3. Update a Department
			4. Delete a Department
			5. Show By ID
			6. Add/change boss to a department
			-1. Back

			""";

	public Integer getOption() {
		Integer option;
		IO.println(options);
		option = IO.readInt();
		return option;
	}

	public Department create() throws OurExceptions {
		IO.print("Department's Name: ");
		String name = IO.readString();
		
		if(name.equals("")) {
			throw new OurExceptions("Name can not be empty");
		}

		Department department = Department.builder().name(name).build();
		return department;
	}

	public void show(List<Department> departments) {
		if (departments.isEmpty()) {
			System.out.println("No Departments found.");
		} else {
			System.out.println("List of Departments:\nID:\t\t\tNAME:\t\t\tBOSS:");
			for (Department department : departments) {
				System.out.println(department.toString());
			}
		}
	}

	public Integer findDepartmentById() {
		IO.print("Department's ID: ");
		Integer clave = IO.readInt();
		return clave;
	}
	
	

	public void readDepartment(Department department) {
		System.out.println(department);
	}

	public Department updateDepartment(Department department) {
		String name;
		IO.println("New Department's Name: ");
		name = IO.readString();
		
		if(name.equals("")) {
			name = department.getName();
		}
		// Department can't change id!
		department.setName(name);
		return department;
	}

	public Integer returnGenericIdToAddBoss(String objectSearch, boolean add) {
		Integer id;
		
		if (add) {
			IO.println("Write an id to search " + objectSearch + " in which boss is going to be inserted");
		} else {
			IO.println("Write an id to search " + objectSearch + " to be inserted into a department");
		}
		
		id = IO.readInt();
		return id;
	}

	public Department addBossToDepartment(Department department, Worker worker) {
		worker.addBoss(department);
		return department;
	}

	public Integer returnGenericIdToDelete() {
		Integer id;
		IO.println("Select Department's ID to delete: ");
		id = IO.readInt();
		return id;
	}
	
	public void error(String error) {
		IO.println(error);
	}

}
