package controller;

import java.util.List;

import dao.WorkerDao;
import jakarta.persistence.EntityManager;
import main.Main;
import models.Worker;
import view.WorkerView;

public class WorkerController {
	
	private final WorkerView workerMenuView;
	private final WorkerDao workerDao;
	
	public WorkerController() {
		workerMenuView = new WorkerView();
		workerDao = new WorkerDao();
	}
	
	
	public void menu() {
		Integer option;
		
		option = 0;
		
		while(option != -1) {
			
			option = workerMenuView.getOption();
			
			switch(option) {
			
			case 1:
				//Create / Add
				add(Main.em);
				break;
			case 2:
				show(Main.em);
				break;
			case 3:
				//Update
				break;
			case 4:
				//delete
				break;
			case 5:
				findById();
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
	
//	public void update(Integer id) {
//		
//		
//	}

}
