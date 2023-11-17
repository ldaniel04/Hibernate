package dao;

import java.util.List;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Main;
import models.Project;

public class ProjectDao{
	private final Logger logger = Logger.getLogger(WorkerDao.class.getName());
	private static final String SELECT_ALL_PROJECTS = "SELECT e FROM Project e";
	
	public void add(Project project, EntityManager em) {
		
		em.getTransaction().begin();
		em.persist(project);
		em.getTransaction().commit();
	}
	
	public Project findById(Integer key) {
		logger.info("findById()");
		
		Main.em.getTransaction().begin();
		Project project = Main.em.find(Project.class, key);
		Main.em.getTransaction().commit();
		return project;
		
	}
	
	public List<Project> show(EntityManager em) {
		TypedQuery<Project> query = em.createQuery(SELECT_ALL_PROJECTS, Project.class);
        return query.getResultList();
	}

	

}
