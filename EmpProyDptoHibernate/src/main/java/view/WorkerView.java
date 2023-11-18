package view;

import java.util.List;

import library.IO;
import models.Department;
import models.Worker;

public class WorkerView {

	
final String options =  """
			
			1. Create a worker
			2. Show workers
			3. Update a worker
			4. Delete a worker
			5. Show by id
			6. Add/change worker to a department
			-1. Exit
			
			""";
	
	
	public Integer getOption() {
		Integer option;
		
		IO.println(options);
		option = IO.readInt();
		return option;
		
		
	}
	
	public Worker create() {
		
		IO.print("Name ?: ");
		String name = IO.readString();
		IO.print("Salary: ? ");
		Double salary = IO.readDouble();
		
		Worker worker = Worker.builder().name(name).salary(salary).build();
		
		return worker;
		
		
	}
	
	public Integer findWorkerById() {
		IO.print("Clave ?: ");
		Integer clave = IO.readInt();
		
		return clave;
	}
	
	public void readWorker(Worker worker) {
		System.out.println(worker);
		
	}
	
	public Worker updateWorker(Worker worker) {
		String name;
		Double salary;
		IO.println("Name?: ");
		name = IO.readString();
		IO.println("Salary?: ");
		salary = IO.readDouble();
		
		//Worker can't change id!
		worker.setName(name);
		worker.setSalary(salary);
		
		return worker;
	}
	
	public void show(List<Worker> workers) {
        if (workers.isEmpty()) {
            System.out.println("No workers found.");
        } else {
            System.out.println("List of Workers:");
            for (Worker worker : workers) {
                System.out.println(worker.toString());
            }
        }
	}
	
	public Integer returnGenericIdToAddDepartment(String objectSearch, boolean add) {
		Integer id;
		
		if(add) {
			IO.println("Write an id to search " + objectSearch + "in which worker is going to be inserted");
		}else {
			IO.println("Write an id to search " + objectSearch + "to be inserted into a department");
		}
		
		id = IO.readInt();
		
		return id;
	}
	
	public Worker addToDepartment(Department department, Worker worker) {
		
//		Integer id;
//		
//		IO.println("Select worker destinations department's id: ");
//
//		id = IO.readInt();
		
		department.addDepart(worker);
		
		return worker;
		
		
	}
	
	
	public Integer returnGenericIdToDelete() {
		Integer id;
		
		IO.println("Select id to delete: ");
		
		id = IO.readInt();
		
		return id;
	}
	
	
		
		
		
		
		
		
	
}
