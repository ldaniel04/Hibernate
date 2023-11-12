package dao;

import jakarta.persistence.EntityManager;
import models.Project;

public class ProjectDao {
	
	public void add(Project project, EntityManager em) {
		
		em.getTransaction().begin();
		em.persist(project);
		em.getTransaction().commit();
	}

}
