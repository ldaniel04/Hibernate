package dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

	

	
	public Optional<Worker> findById(Object id) {
		logger.info("findById");
		
		return null;
	}

	
	public void findAll() {
		logger.info("findAll");
		
		
	}

	
	public Boolean delete(Object entity) {
		return null;
	}

}
