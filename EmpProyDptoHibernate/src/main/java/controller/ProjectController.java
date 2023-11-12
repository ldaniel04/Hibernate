package controller;

import dao.ProjectDao;
import jakarta.persistence.EntityManager;
import main.Main;
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
				// Show
				break;
			case 3:
				// Update
				break;
			case 4:
				// delete
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

}
