package controller;

import java.util.List;

import dao.DepartmentDao;
import jakarta.persistence.EntityManager;
import main.Main;
import models.Department;
import models.Worker;
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

		Department department = departmentMenuView.create();

		departmentDao.add(department, em);

	}

	public void show(EntityManager em) {
		List<Department> allDepartments = departmentDao.show(em);
		departmentMenuView.show(allDepartments);
	}

	public void findById() {
		Integer id = departmentMenuView.findDepartmentById();
		Department department = departmentDao.findById(id);
		departmentMenuView.readDepartment(department);
	}

	public void update() {

		Integer id = departmentMenuView.findDepartmentById();

		Department department = departmentDao.findById(id);

		department = departmentMenuView.updateDepartment(department);

		departmentDao.update(department);

	}

}
