package controller;

import java.util.List;

import dao.ProjectDao;
import dao.WorkerDao;
import exceptions.OurExceptions;
import library.IO;
import models.Project;
import models.Worker;
import view.ProjectView;

public class ProjectController {

	private final ProjectView projectMenuView;
	private final ProjectDao projectDao;
	private final WorkerDao workerDao;

	public ProjectController() {
		projectMenuView = new ProjectView();
		projectDao = new ProjectDao();
		workerDao = new WorkerDao();
	}

	public void menu() {
		Integer option;

		option = 0;

		while (option != -1) {

			option = projectMenuView.getOption();

			switch (option) {

			case 1:
				add();
				break;
			case 2:
				show();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			case 5:
				findById();
				break;
			case 6:
				addWorkerToProject();
				break;
			case -1:
				break;
			default:
				System.out.println("No valid option");
			}
		}
	}

	/**
	 * Create a project and add it to the database
	 */
	public void add() {
		Project project;
		try {
			project = projectMenuView.create();
			projectDao.add(project);
		} catch (OurExceptions e) {
			// TODO Auto-generated catch block
			IO.println(e.getMessage());
		}
	}

	/**
	 * Show all projects
	 */
	public void show() {
		List<Project> allProjects = projectDao.show();
		projectMenuView.show(allProjects);
	}

	/**
	 * Find and update a project
	 */
	public void update() {
		Integer id = projectMenuView.findProjectById();
		Project project = projectDao.findById(id);
		project = projectMenuView.updateProject(project);
		projectDao.update(project);

	}

	/**
	 * Metodo que elimina un Proyecto. Recupera el proyecto por su ID, verifica su
	 * existencia y lo elimina. Si el proyecto no existe, muestra un mensaje de
	 * error.
	 */
	public void delete() {
		Integer id = projectMenuView.returnGenericIdToDelete();
		Project project = projectDao.findById(id);

		if (project == null) {
			projectMenuView.error("That project does not exist! Returning to project menu");
			return;
		}

		projectDao.delete(project);
	}

	/**
	 * Find a project by id
	 */
	public void findById() {
		Integer id = projectMenuView.findProjectById();
		Project project = projectDao.findById(id);
		projectMenuView.readProject(project);
	}

	/**
	 * Asigna un proyecto existente a un trabajador existente.
	 * Recupera un Proyecto por su ID y un Trabajador por su ID. 
	 * Verifica la existencia del proyecto y del trabajador.
	 * Y termina asociando mediante un update.
	 */
	public void addWorkerToProject() {

		Integer idProject = projectMenuView.returnGenericIdForAdding("project", false);
		Project project = projectDao.findById(idProject);
		if (project == null) {
			projectMenuView.error("That project does not exist! Returning to project menu");
			return;
		}

		Worker worker = workerDao.findById(projectMenuView.returnGenericIdForAdding("worker", true));
		if (worker == null) {
			projectMenuView.error("That worker does not exist! Returning to project menu");
			return;
		}
		project = projectMenuView.addWorkerTo(project, worker);
		projectDao.update(project);
	}
}