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

	public Integer getOption() {
		Integer option;
		IO.println(options);
		option = IO.readInt();
		return option;
	}

	public Project create() throws OurExceptions {
		IO.print("Project's Name: ");
		String name = IO.readString();

		if (name.equals("")) {
			throw new OurExceptions("Name can not be empty");
		}

		Project project = Project.builder().name(name).build();
		return project;
	}

	public void readProject(Project project) {
		System.out.println(project);
	}

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

	public Integer findProjectById() {
		IO.print("Project's ID: ");
		Integer clave = IO.readInt();
		return clave;
	}

	public Project updateProject(Project project) {
		String name;
		IO.println("New project's name: ");
		name = IO.readString();

		if (name.equals("")) {
			name = project.getName();
		}

		// Id can't change id!
		project.setName(name);
		return project;
	}

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

	public void error(String error) {
		IO.println(error);
	}

	public Integer returnGenericIdToDelete() {
		Integer id;
		IO.println("Select Project's ID to delete: ");
		id = IO.readInt();
		return id;
	}
}
