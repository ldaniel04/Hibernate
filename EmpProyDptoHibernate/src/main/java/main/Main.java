package main;

import controller.MainController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import library.IO;
import models.Worker;

public class Main {
	
	public static EntityManager em = Persistence.createEntityManagerFactory("unidad-persistencia").createEntityManager();

	public static void main(String[] args) {
	
		Persistence.generateSchema("unidad-persistencia", null);
		
//		em = Persistence.createEntityManagerFactory("unidad-persistencia").createEntityManager();
		//Need to create interface CRUD as pro has in his hibernate's project??????
		new MainController();
		
		
	}
	
	
	
//	public static void add() {
//		em.getTransaction().begin();;
//		String name;
//		Double salary;
//		Worker worker;
//		Worker workerRecover;
//		
//		System.out.println("Name?: ");
//		name = IO.readString();
//		System.out.println("Salary? :");
//		salary = IO.readDouble();
//		
//		
//		worker = Worker.builder().name(name).salary(salary).build();
//		
//		System.out.println(worker.getName());
//		
//		em.persist(worker);
//		
//		
//		
//		workerRecover = em.find(Worker.class, 1);
//		
//		System.out.println(workerRecover.getId() + " " + workerRecover.getName() + " " + workerRecover.getSalary());
//		
//		em.getTransaction().commit();
//		
//		
//		
//		
//	}

}
