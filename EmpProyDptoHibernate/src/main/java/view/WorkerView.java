package view;

import java.util.List;

import exceptions.OurExceptions;
import library.IO;
import models.Department;
import models.Project;
import models.Worker;

public class WorkerView {

	final String options = """

			1. Create a worker
			2. Show workers
			3. Update a worker
			4. Delete a worker
			5. Show by id
			6. Add/change worker to a department
			7. Add/change worker to a project
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
	 * Creates and returns a new Worker based on user input.
	 *
	 * @return a Worker object created from user input.
	 * @throws OurExceptions if the department name is empty.
	 */
	public Worker create() throws OurExceptions {

		IO.print("Worker's Name: ");
		String name = IO.readString();
		if (name.equals("")) {

			throw new OurExceptions("Name can not be empty");
		}
		IO.print("Worker's Salary: ");
		Double salary = IO.readDouble();

		Worker worker = Worker.builder().name(name).salary(salary).build();

		return worker;

	}

	/**
	 * Prompts the user to enter a worker's ID and returns the entered value.
	 *
	 * @return an Integer representing the worker's ID entered by the user.
	 */
	public Integer findWorkerById() {
		IO.print("Worker's ID: ");
		Integer clave = IO.readInt();

		return clave;
	}

	/**
	 * Displays the details of a worker.
	 *
	 * @param worker the Worker object whose details will be displayed.
	 */
	public void readWorker(Worker worker) {
		System.out.println(worker);

	}

	/**
	 * Updates the details of a worker based on user input and returns the updated department.
	 *
	 * @param department the Worker object to be updated.
	 * @return the updated Worker object.
	 */
	public Worker updateWorker(Worker worker) {
		String name;
		Double salary;
		IO.println("New worker's name: ");
		name = IO.readString();

		if (name.equals("")) {
			name = worker.getName();
		}
		IO.println("New salary: ");
		salary = IO.readDouble();

		worker.setName(name);
		worker.setSalary(salary);

		return worker;
	}

	/**
	 * Displays a list of workers with their details.
	 *
	 * @param departments the List of Worker objects to be displayed.
	 */
	public void show(List<Worker> workers) {
		if (workers.isEmpty()) {
			System.out.println("No workers found.");
		} else {
			System.out.println("List of Workers:\nID:\tNAME:\tSALARY:\tDEPARTMENT:");
			for (Worker worker : workers) {
				System.out.println(worker.toString());
			}
		}
	}

	/**
	 * Prompts the user to enter an ID for searching and adding a boss to or inserting into a worker.
	 *
	 * @param objectSearch a String representing the type of object to be searched 
	 * @param add a boolean indicating whether the operation is for adding a boss or inserting into a worker.
	 * @return an Integer representing the entered ID.
	 */
	public Integer returnGenericIdForAdding(String objectSearch, boolean add) {
		Integer id;

		if (add) {
			IO.println("Write an id to search a " + objectSearch + " in which worker is going to be inserted");
		} else {
			IO.println("Write an id to search a " + objectSearch + " to be inserted into");
		}

		id = IO.readInt();

		return id;
	}

	/**
	 * Adds a worker as a employee to a department and returns the updated worker.
	 *
	 * @param department the Department object to which the boss is added.
	 * @param worker the Worker object to be added as a boss.
	 * @return the updated Worker object.
	 */
	public Worker addToDepartment(Department department, Worker worker) {

		department.addDepart(worker);

		return worker;

	}

	/**
	 * Metodo que recoje un projecto y un trabajador. Llama a un metodo de la clase
	 * trabajador donde añadiran su trabajor y proyecto a sus respectivas
	 * colecciones
	 * 
	 * @param project
	 * @param worker
	 * @return worker
	 */
	public Worker addToProject(Project project, Worker worker) {
		worker.addWorkerToProject(project);
		return worker;
	}

	/**
	 * Prompts the user to enter an ID for selecting a worker to delete.
	 *
	 * @return an Integer representing the entered worker ID to delete.
	 */
	public Integer returnGenericIdToDelete() {
		Integer id;
		IO.println("Select Worker's ID to delete: ");
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
