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
				// Create / Add
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

	public void show() {
		List<Project> allProjects = projectDao.show();
		projectMenuView.show(allProjects);
	}

	public void update() {
		Integer id = projectMenuView.findProjectById();
		Project project = projectDao.findById(id);
		project = projectMenuView.updateProject(project);
		projectDao.update(project);

	}

	public void delete() {//ESTE NO BORRA PORQUE HAY QUE CONTROLAR LOS WORKERS
		Integer id = projectMenuView.returnGenericIdToDelete();
		Project project = projectDao.findById(id);

		if (project == null) {
			projectMenuView.error("That project does not exist! Returning to project menu");
			return;
		}

		projectDao.delete(project);
	}

	public void findById() {
		Integer id = projectMenuView.findProjectById();
		Project project = projectDao.findById(id);
		projectMenuView.readProject(project);
	}

	public void addWorkerToProject(){//ESTTE NO AÑADE 

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