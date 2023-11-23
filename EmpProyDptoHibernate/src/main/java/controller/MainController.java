package controller;

import jakarta.persistence.EntityManager;
import view.MainView;

public class MainController {
	
	static EntityManager em = null;
	
	public MainController() {
		
		Integer option = 0;
		DepartmentController departmentController = new DepartmentController();
		WorkerController workerController = new WorkerController();
		ProjectController projectController = new ProjectController();
		
		
		while(option != -1) {
			
			option = MainView.getOptionMainMenu();
			
			switch(option) {
			
			case 1:
				workerController.menu();
				break;
			case 2:
				departmentController.menu();
				break;
			case 3:
				projectController.menu();
			case -1:
				break;
			default:
				System.out.println("No valid option");
			
			
			}
			
			
			
		}
	}
	
	
	

}
