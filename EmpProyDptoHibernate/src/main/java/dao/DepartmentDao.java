package dao;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Main;
import models.Department;
import models.Worker;

public class DepartmentDao {
	private final Logger logger = Logger.getLogger(DepartmentDao.class.getName());
	private static final String SELECT_ALL_DEPARTMENTS = "SELECT e FROM Department e";

	public void add(Department department, EntityManager em) {
		try {
			em.getTransaction().begin();
			em.persist(department);
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Problema al crear el departamento.");
		}
	}

	public List<Department> show(EntityManager em) {
		TypedQuery<Department> query = em.createQuery(SELECT_ALL_DEPARTMENTS, Department.class);
		return query.getResultList();
	}

	public Optional<Department> findById(Object id) {
		logger.info("findById");
		return null;
	}

	public Department findById(Integer key) {
		logger.info("findById()");
		
		Main.em.getTransaction().begin();
		Department department = Main.em.find(Department.class, key);
		Main.em.getTransaction().commit();
		return department;
	}

	public void update(Department department) {
		Main.em.getTransaction().begin();
		Main.em.merge(department);
		Main.em.getTransaction().commit();
	}

	public void delete(Department department, List<Worker> workers) {
		Main.em.getTransaction().begin();

		for (Worker worker : workers) {
			if (worker != null && worker.getDepart() != null && worker.getDepart().getId() == department.getId()) {
				worker.setDepart(null);
			}
		}

		Main.em.remove(department);
		Main.em.getTransaction().commit();
	}
}
