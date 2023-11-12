package controller;

import dao.DepartmentDao;
import jakarta.persistence.EntityManager;
import main.Main;
import models.Department;
import view.DepartmentView;

public class DepartmentController {

	private final DepartmentView departmentMenuView;
	private final DepartmentDao departmentDao;

	public DepartmentController() {
		departmentMenuView = new DepartmentView();
		departmentDao = new DepartmentDao();
	}

	public void menu() {
		Integer option;

		option = 0;

		while (option != -1) {

			option = departmentMenuView.getOption();

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

		Department department = departmentMenuView.create();

		departmentDao.add(department, em);

	}

}
