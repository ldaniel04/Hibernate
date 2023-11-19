package controller;

import java.util.List;

import dao.DepartmentDao;
import dao.ProjectDao;
import dao.WorkerDao;
import exceptions.OurExceptions;
import jakarta.persistence.EntityManager;
import library.IO;
import main.Main;
import models.Department;
import models.Project;
import models.Worker;
import view.WorkerView;

public class WorkerController {

	private final WorkerView workerMenuView;
	private final WorkerDao workerDao;
	private final DepartmentDao departmentDao;  //To add worker to department (need to find departents)
	private final ProjectDao projectDao;

	public WorkerController() {
		workerMenuView = new WorkerView();
		workerDao = new WorkerDao();
		departmentDao = new DepartmentDao();
		projectDao = new ProjectDao();
	}

	public void menu() {
		Integer option;

		option = 0;

		while (option != -1) {

			option = workerMenuView.getOption();

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
				deleteWorker();
				break;
			case 5:
				findById();
				break;
			case 6:
				addDepartmentToWorker();
				break;
			case 7:
				addProjectToWorker();
				break;
			case -1:
				break;
			default:
				System.out.println("No valid option");

			}

		}

	}

	public void add(EntityManager em) {

		Worker worker;
		try {
			worker = workerMenuView.create();
			workerDao.add(worker, em);
		} catch (OurExceptions e) {
			// TODO Auto-generated catch block
			IO.println(e.getMessage());
			return;
		}

		

	}

	public void show(EntityManager em) {
		List<Worker> allWorkers = workerDao.show(em);
		workerMenuView.show(allWorkers);
	}

	public void findById() {
		Integer id = workerMenuView.findWorkerById();
		Worker worker = workerDao.findById(id);
		workerMenuView.readWorker(worker);
	}

	public void update() {
		
		Integer id = workerMenuView.findWorkerById();
		
		Worker worker = workerDao.findById(id);
		
		if(worker == null) {
			workerMenuView.error("That worker does not exist! Returning to main menu");
			return;
		}
		
		worker = workerMenuView.updateWorker(worker);
		
		workerDao.update(worker);
		
		
	}
	
	public void addDepartmentToWorker() {
		
		Integer id = workerMenuView.returnGenericIdForAdding("worker", false);
		
		Worker worker = workerDao.findById(id);
		if(worker == null) {
			workerMenuView.error("That worker does not exist! Returning to main menu");
			return;
		}
		Department department = departmentDao.findById(workerMenuView.returnGenericIdForAdding("department", true));
		
		if(department == null) {
			workerMenuView.error("That department does not exist! Returning to main menu");
			return;
		}
		
		worker = workerMenuView.addToDepartment(department, worker); //Returns worker with department
		
		workerDao.update(worker);
		
	}
	
	
	public void addProjectToWorker() {
	
		Integer idWorker = workerMenuView.returnGenericIdForAdding("worker", false);
		Worker worker =  workerDao.findById(idWorker);
		if (worker == null) {
			workerMenuView.error("That worker does not exist! Returning to main menu");
			return;
		}
		
		Project project = projectDao.findById(workerMenuView.returnGenericIdForAdding("project", true));
		if (project == null) {
			workerMenuView.error("That project does not exist! Returning to main menu");
			return;
		}

		worker = workerMenuView.addToProject(project, worker);
		workerDao.update(worker);
	}
	
	public void deleteWorker() {
		Integer id = workerMenuView.returnGenericIdToDelete();
		Worker worker = workerDao.findById(id);
		
		if(worker == null) {
			workerMenuView.error("That worker does not exist! Returning to main menu");
			return;
		}
		
		Department department = worker.getDepart();
		workerDao.delete(worker, department);

	}
}