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

	public Integer getOption() {
		Integer option;

		IO.println(options);
		option = IO.readInt();
		return option;

	}

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

	public Integer findWorkerById() {
		IO.print("Worker's ID: ");
		Integer clave = IO.readInt();

		return clave;
	}

	public void readWorker(Worker worker) {
		System.out.println(worker);

	}

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

		// Worker can't change id!
		worker.setName(name);
		worker.setSalary(salary);

		return worker;
	}

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

	public Worker addToDepartment(Department department, Worker worker) {

		department.addDepart(worker);

		return worker;

	}
	
	public Worker addToProject(Project project, Worker worker) {

		project.addProjectToWorker(worker);
		return worker;

	}

	public Integer returnGenericIdToDelete() {
		Integer id;
		IO.println("Select Worker's ID to delete: ");
		id = IO.readInt();
		return id;
	}

	public void error(String error) {
		IO.println(error);
	}

}
