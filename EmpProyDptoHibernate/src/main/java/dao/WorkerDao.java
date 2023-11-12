package dao;

import jakarta.persistence.EntityManager;
import models.Worker;

public class WorkerDao {

	public void add(Worker worker, EntityManager em) {

		em.getTransaction().begin();
		em.persist(worker);
		em.getTransaction().commit();
		
	}

}
