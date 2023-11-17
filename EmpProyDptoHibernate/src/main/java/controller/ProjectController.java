package controller;

import java.util.List;

import dao.ProjectDao;
import jakarta.persistence.EntityManager;
import main.Main;
import models.Department;
import models.Project;
import view.ProjectView;

public class ProjectController {

	private final ProjectView projectMenuView;
	private final ProjectDao projectDao;

	public ProjectController() {
		projectMenuView = new ProjectView();
		projectDao = new ProjectDao();
	}

	public void menu() {
		Integer option;

		option = 0;

		while (option != -1) {

			option = projectMenuView.getOption();

			switch (option) {

			case 1:
				// Create / Add
				add(Main.em);
				break;
			case 2:
				 show(Main.em);
				break;
			case 3:
				update();
				break;
			case 4:
				// delete
				break;
			case 5:
				findById();
				break;
			case -1:
				break;
			default:
				// return string ??

			}

		}

	}

	public void add(EntityManager em) {

		Project project = projectMenuView.create();

		projectDao.add(project, em);

	}
	
	public void show(EntityManager em) {
		List<Project> allProjects = projectDao.show(em);
		projectMenuView.show(allProjects);
	}

	public void findById() {
		Integer id = projectMenuView.findProjectById();
		Project project = projectDao.findById(id);
		projectMenuView.readProject(project);
	}
	
	public void update() {

		Integer id = projectMenuView.findProjectById();

		Project project = projectDao.findById(id);

		project = projectMenuView.updateProject(project);

		projectDao.update(project);

	}

}
