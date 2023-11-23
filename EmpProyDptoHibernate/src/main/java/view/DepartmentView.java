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
	/**
	 * Prompts the user with available options, reads and returns the selected option.
	 *
	 * @return an Integer representing the user-selected option.
	 */
	public Integer getOption() {
		Integer option;
		IO.println(options);
		option = IO.readInt();
		return option;
	}
	/**
	 * Creates and returns a new Department based on user input.
	 *
	 * @return a Department object created from user input.
	 * @throws OurExceptions if the department name is empty.
	 */
	public Department create() throws OurExceptions {
		IO.print("Department's Name: ");
		String name = IO.readString();
		
		if(name.equals("")) {
			throw new OurExceptions("Name can not be empty");
		}

		Department department = Department.builder().name(name).build();
		return department;
	}
	/**
	 * Displays a list of departments with their details.
	 *
	 * @param departments the List of Department objects to be displayed.
	 */
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
	/**
	 * Prompts the user to enter a department's ID and returns the entered value.
	 *
	 * @return an Integer representing the department's ID entered by the user.
	 */
	public Integer findDepartmentById() {
		IO.print("Department's ID: ");
		Integer clave = IO.readInt();
		return clave;
	}
	
	
	/**
	 * Displays the details of a department.
	 *
	 * @param department the Department object whose details will be displayed.
	 */
	public void readDepartment(Department department) {
		System.out.println(department);
	}
	/**
	 * Updates the details of a department based on user input and returns the updated department.
	 *
	 * @param department the Department object to be updated.
	 * @return the updated Department object.
	 */
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
	/**
	 * Prompts the user to enter an ID for searching and adding a boss to or inserting into a department.
	 *
	 * @param objectSearch a String representing the type of object to be searched 
	 * @param add a boolean indicating whether the operation is for adding a boss or inserting into a department.
	 * @return an Integer representing the entered ID.
	 */
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
	/**
	 * Adds a worker as a boss to a department and returns the updated department.
	 *
	 * @param department the Department object to which the boss is added.
	 * @param worker the Worker object to be added as a boss.
	 * @return the updated Department object.
	 */
	public Department addBossToDepartment(Department department, Worker worker) {
		worker.addBoss(department);
		return department;
	}
	/**
	 * Prompts the user to enter an ID for selecting a department to delete.
	 *
	 * @return an Integer representing the entered department ID to delete.
	 */
	public Integer returnGenericIdToDelete() {
		Integer id;
		IO.println("Select Department's ID to delete: ");
		id = IO.readInt();
		return id;
	}
	/**
	 * Displays an error message.
	 *
	 * @param error a String representing the error message to be displayed.
	 */
	public void error(String error) {
		IO.println(error);
	}

}
