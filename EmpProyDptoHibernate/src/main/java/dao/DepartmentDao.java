package dao;

import java.util.List;

import jakarta.persistence.TypedQuery;
import main.Main;
import models.Department;
import models.Worker;

public class DepartmentDao {
	private static final String SELECT_ALL_DEPARTMENTS = "SELECT e FROM Department e";

	/**
	 * Adds a new department to the database.
	 *
	 * @param department the Department object to be added to the database.
	 */
	public void add(Department department) {
		try {
			Main.em.getTransaction().begin();
			Main.em.persist(department);
			Main.em.getTransaction().commit();
		} catch (Exception e) {
			if (Main.em.getTransaction().isActive()) {
				Main.em.getTransaction().rollback();
			}
			System.out.println("Problema al crear el departamento.");
		}
	}

	/**
	 * Retrieves and returns a list of all departments from the database.
	 *
	 * @return a List of Department objects representing all departments in the database.
	 */
	public List<Department> show() {
		TypedQuery<Department> query = Main.em.createQuery(SELECT_ALL_DEPARTMENTS, Department.class);
		return query.getResultList();
	}

	

	/**
	 * Retrieves and returns a department from the database based on the provided key.
	 *
	 * @param key the unique identifier of the department to be retrieved.
	 * @return a Department object representing the department with the specified key, or null if not found.
	 */
	public Department findById(Integer key) {
		
		Main.em.getTransaction().begin();
		Department department = Main.em.find(Department.class, key);
		Main.em.getTransaction().commit();
		return department;
	}
	
	

	/**
	 * Updates an existing department in the database.
	 *
	 * @param department the Department object to be updated in the database.
	 */
	public void update(Department department) {
		Main.em.getTransaction().begin();
		Main.em.merge(department);
		Main.em.getTransaction().commit();
	}


	/**
	 * Deletes a department from the database and removes its association with specified workers.
	 *
	 * @param department the Department object to be deleted from the database.
	 * @param workers a List of Worker objects associated with the department, whose associations will be removed.
	 */
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
