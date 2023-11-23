package view;

import java.util.List;

import exceptions.OurExceptions;
import library.IO;
import models.Project;
import models.Worker;

public class ProjectView {

	final String options = """

			1. Create a project
			2. Show projects
			3. Update a project
			4. Delete a project
			5. Show by ID
			6. Add/Change Project to a Worker
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
	 * Creates and returns a new Project based on user input.
	 *
	 * @return a Project object created from user input.
	 * @throws OurExceptions if the department name is empty.
	 */
	public Project create() throws OurExceptions {
		IO.print("Project's Name: ");
		String name = IO.readString();

		if (name.equals("")) {
			throw new OurExceptions("Name can not be empty");
		}

		Project project = Project.builder().name(name).build();
		return project;
	}

	/**
	 * Displays the details of a projet.
	 *
	 * @param department the Project object whose details will be displayed.
	 */
	public void readProject(Project project) {
		System.out.println(project);
	}

	/**
	 * Displays a list of projects with their details.
	 *
	 * @param departments the List of Project objects to be displayed.
	 */
	public void show(List<Project> projects) {
		if (projects.isEmpty()) {
			System.out.println("No projects found.");
		} else {
			System.out.println("List of Projects:\nID:\tNAME:");
			for (Project project : projects) {
				System.out.println(project.toString());
			}
		}
	}

	/**
	 * Prompts the user to enter a project's ID and returns the entered value.
	 *
	 * @return an Integer representing the department's ID entered by the user.
	 */
	public Integer findProjectById() {
		IO.print("Project's ID: ");
		Integer clave = IO.readInt();
		return clave;
	}

	/**
	 * Updates the details of a project based on user input and returns the updated department.
	 *
	 * @param department the Project object to be updated.
	 * @return the updated Project object.
	 */
	public Project updateProject(Project project) {
		String name;
		IO.println("New project's name: ");
		name = IO.readString();

		if (name.equals("")) {
			name = project.getName();
		}

		
		project.setName(name);
		return project;
	}

	/**
	 * Prompts the user to enter an ID for searching and adding a boss to or inserting into a project.
	 *
	 * @param objectSearch a String representing the type of object to be searched 
	 * @param add a boolean indicating whether the operation is for adding a project or inserting into a worker.
	 * @return an Integer representing the entered ID.
	 */
	public Integer returnGenericIdForAdding(String objectSearch, boolean add) {
		Integer id;

		if (add) {
			IO.println("Write an id to search a " + objectSearch + " in which project is going to be inserted");
		} else {
			IO.println("Write an id to search a " + objectSearch + " to be assigned into");
		}

		id = IO.readInt();

		return id;
	}

	/**
	 * Metodo que recoje un projecto y un trabajador. Llama a un metodo de la clase
	 * projecto donde añadiran su proyecto y trabajor a sus respectivas
	 * colecciones
	 * 
	 * @param project
	 * @param worker
	 * @return
	 */
	public Project addWorkerTo(Project project, Worker worker) {
		project.addProjectToWorker(worker);
		return project;

	}
	
	/**
	 * Displays an error message.
	 *
	 * @param error a String representing the error message to be displayed.
	 */
	public void error(String error) {
		IO.println(error);
	}

	/**
	 * Prompts the user to enter an ID for selecting a project to delete.
	 *
	 * @return an Integer representing the entered department ID to delete.
	 */
	public Integer returnGenericIdToDelete() {
		Integer id;
		IO.println("Select Project's ID to delete: ");
		id = IO.readInt();
		return id;
	}
}
