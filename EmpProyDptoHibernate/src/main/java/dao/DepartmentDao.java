package dao;

import jakarta.persistence.EntityManager;
import models.Department;

public class DepartmentDao {
	
	
	public void add(Department department, EntityManager em) {
		
		em.getTransaction().begin();
		em.persist(department);
		em.getTransaction().commit();
	}

}
