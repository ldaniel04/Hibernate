package dao;

import java.util.List;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Main;
import models.Worker;

public class WorkerDao {
	private final Logger logger = Logger.getLogger(WorkerDao.class.getName());
	private static final String SELECT_ALL_WORKERS = "SELECT e FROM Worker e";

	
	public void add(Worker worker, EntityManager em) {
		logger.info("add()");
		em.getTransaction().begin();
		em.persist(worker);
		
		em.getTransaction().commit();
		
	}

	
	public List<Worker> show(EntityManager em) {
		TypedQuery<Worker> query = em.createQuery(SELECT_ALL_WORKERS, Worker.class);
        return query.getResultList();
	}

	

	
	

	public void update(Worker worker) {
		logger.info(SELECT_ALL_WORKERS);
		
		Main.em.getTransaction().begin();
		
		Main.em.merge(worker);
		
		Main.em.getTransaction().commit();
		
		
	}
	
	public Worker findById(Integer key) {
		logger.info("findById()");
		
		Main.em.getTransaction().begin();
		Worker worker = Main.em.find(Worker.class, key);
		Main.em.getTransaction().commit();
		return worker;
		
	}
		
		
	

	
	public Boolean delete(Object entity) {
		return null;
	}

}
