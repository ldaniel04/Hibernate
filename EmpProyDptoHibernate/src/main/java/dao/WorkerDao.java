package dao;

import java.util.List;
import java.util.logging.Logger;
import jakarta.persistence.TypedQuery;
import main.Main;
import models.Department;
import models.Worker;

public class WorkerDao {
	private final Logger logger = Logger.getLogger(WorkerDao.class.getName());
	private static final String SELECT_ALL_WORKERS = "SELECT e FROM Worker e";

	/**
	 * Adds a new worker to the database.
	 *
	 * @param worker the Worker object to be added to the database.
	 */

	public void add(Worker worker) {
		logger.info("add()");
		Main.em.getTransaction().begin();
		Main.em.persist(worker);

		Main.em.getTransaction().commit();

	}

	/**
	 * Retrieves and returns a list of all workers from the database.
	 *
	 * @return a List of Worker objects representing all workers in the database.
	 */
	public List<Worker> show() {
		TypedQuery<Worker> query = Main.em.createQuery(SELECT_ALL_WORKERS, Worker.class);
		return query.getResultList();
	}

	/**
	 * Actualiza la información de un trabajador en la base de datos. Actualiza los
	 * datos del trabajador proporcionado en la base de datos. Utiliza la operación
	 * 'merge' para sincronizar el estado del trabajador con la base de datos y
	 * 'persist' para hacer cambios permanentes.
	 * 
	 * @param worker
	 */
	public void update(Worker worker) {
		logger.info(SELECT_ALL_WORKERS);

		Main.em.getTransaction().begin();
		Main.em.merge(worker);
		Main.em.persist(worker);
		Main.em.getTransaction().commit();

	}

	/**
	 * Retrieves and returns a worker from the database based on the provided key.
	 *
	 * @param key the unique identifier of the worker to be retrieved.
	 * @return a Worker object representing the worker with the specified key, or null if not found.
	 */
	public Worker findById(Integer key) {
		logger.info("findById()");

		Main.em.getTransaction().begin();
		Worker worker = Main.em.find(Worker.class, key);
		Main.em.getTransaction().commit();
		return worker;

	}

	/**
	 * Deletes a worker from the database and updates associated department information if applicable.
	 *
	 * @param worker the Worker object to be deleted from the database.
	 * @param department the associated Department object, used to update the boss reference if needed.
	 * @return true if the worker is successfully deleted, false otherwise.
	 */
	public Boolean delete(Worker worker, Department department) {

		Main.em.getTransaction().begin();

		if (worker.getDepart() != null && department.getBoss() != null
				&& department.getBoss().getId() == worker.getId()) {
			department.setBoss(null);
			Main.em.remove(worker);
		} else {
			Main.em.remove(worker);
		}

		Main.em.getTransaction().commit();

		return null; // Need create inWorkerVier something that reads this or change this return to a
						// void
	}

}
