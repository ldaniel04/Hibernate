package view;

import java.util.List;

import library.IO;
import models.Department;
import models.Worker;

public class DepartmentView {

	final String options = """

			1. Create a department
			2. Show departments
			3. Update a department
			4. Delete a department
			5. ShowByID
			6. Add/change boss to a department
			-1. Exit

			""";

	public Integer getOption() {
		Integer option;

		IO.println(options);
		option = IO.readInt();
		return option;

	}

	public Department create() {

		IO.print("name: ?");
		String name = IO.readString();

		Department department = Department.builder().name(name).build();

		return department;

	}

	public void show(List<Department> departments) {
		if (departments.isEmpty()) {
			System.out.println("No departments found.");
		} else {
			System.out.println("List of Departments:");
			for (Department department : departments) {
				System.out.println(department.toString());
			}
		}
	}

	public Integer findDepartmentById() {
		IO.print("Clave ?: ");
		Integer clave = IO.readInt();

		return clave;
	}

	public void readDepartment(Department department) {
		System.out.println(department);

	}

	public Department updateDepartment(Department department) {
		String name;
		IO.println("Name?: ");
		name = IO.readString();

		// Worker can't change id!
		department.setName(name);

		return department;
	}

	public Integer returnGenericIdToAddBoss(String objectSearch, boolean add) {
		Integer id;

		if (add) {
			IO.println("Write an id to search " + objectSearch + "in which boss is going to be inserted");
		} else {
			IO.println("Write an id to search " + objectSearch + "to be inserted into a department");
		}

		id = IO.readInt();

		return id;
	}

	public Department addBossToDepartment(Department department, Worker worker) {

//		Integer id;
//		
//		IO.println("Select department to add boss: ");
//
//		id = IO.readInt();

		worker.addBoss(department);

		return department;

	}

	public Integer returnGenericIdToDelete() {
		Integer id;

		IO.println("Select id to delete: ");

		id = IO.readInt();

		return id;
	}

}
