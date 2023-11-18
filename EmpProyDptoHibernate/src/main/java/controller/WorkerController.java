package controller;

import java.util.List;

import dao.DepartmentDao;
import dao.WorkerDao;
import jakarta.persistence.EntityManager;
import main.Main;
import models.Department;
import models.Worker;
import view.WorkerView;

public class WorkerController {

	private final WorkerView workerMenuView;
	private final WorkerDao workerDao;
	private final DepartmentDao departmentDao;  //To add worker to department (need to find departents)

	public WorkerController() {
		workerMenuView = new WorkerView();
		workerDao = new WorkerDao();
		departmentDao = new DepartmentDao();
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
			case -1:
				break;
			default:
				// return string ??

			}

		}

	}

	public void add(EntityManager em) {

		Worker worker = workerMenuView.create();

		workerDao.add(worker, em);

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
		
		worker = workerMenuView.updateWorker(worker);
		
		workerDao.update(worker);
		
		
	}
	
	public void addDepartmentToWorker() {
		
		Integer id = workerMenuView.returnGenericIdToAddDepartment("worker", false);
		
		Worker worker = workerDao.findById(id);
		Department department = departmentDao.findById(workerMenuView.returnGenericIdToAddDepartment("department", true));
		
		worker = workerMenuView.addToDepartment(department, worker); //Returns worker with department
		
		workerDao.update(worker);
		
	}
	
	public void deleteWorker() {
		
		Integer id = workerMenuView.returnGenericIdToDelete();
		
		Worker worker = workerDao.findById(id);
		Department department = worker.getDepart();
		
		workerDao.delete(worker, department);
				
		
	}

}
