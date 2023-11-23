package dao;

import java.util.List;
import java.util.logging.Logger;
import jakarta.persistence.TypedQuery;
import main.Main;
import models.Project;

public class ProjectDao {
	private final Logger logger = Logger.getLogger(WorkerDao.class.getName());
	private static final String SELECT_ALL_PROJECTS = "SELECT e FROM Project e";

	public void add(Project project) {
		Main.em.getTransaction().begin();
		Main.em.persist(project);
		Main.em.getTransaction().commit();
	}

	public Project findById(Integer key) {
		logger.info("findById()");

		Main.em.getTransaction().begin();
		Project project = Main.em.find(Project.class, key);
		Main.em.getTransaction().commit();
		return project;

	}

	public List<Project> show() {
		TypedQuery<Project> query = Main.em.createQuery(SELECT_ALL_PROJECTS, Project.class);
		return query.getResultList();
	}

	/**
	 * Actualiza la información de un proyecto en la base de datos. Actualiza los
	 * datos del proyecto proporcionado en la base de datos. Utiliza la operación
	 * 'merge' para sincronizar el estado del proyecto con la base de datos y
	 * 'persist' para hacer cambios permanentes.
	 * 
	 * @param project
	 */
	public void update(Project project) {
		logger.info(SELECT_ALL_PROJECTS);

		Main.em.getTransaction().begin();
		Main.em.merge(project);
		Main.em.persist(project);
		Main.em.getTransaction().commit();
	}

	/**
	 * Elimina un proyecto de la base de datos. Elimina el proyecto proporcionado de
	 * la base de datos y limpia las referencias en la tabla JOIN. Verifica si
	 * el proyecto no es nulo antes de proceder con la eliminación.
	 * 
	 * @param project
	 */
	public void delete(Project project) {
		Main.em.getTransaction().begin();
		if (project != null) {
			project.getWorkers().forEach(worker -> worker.getProjects().remove(project));
			project.getWorkers().clear();
			Main.em.remove(project);
		}
		Main.em.getTransaction().commit();
	}
}
