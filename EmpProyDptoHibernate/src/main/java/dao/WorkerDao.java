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

	public void add(Worker worker) {
		logger.info("add()");
		Main.em.getTransaction().begin();
		Main.em.persist(worker);

		Main.em.getTransaction().commit();

	}

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

	public Worker findById(Integer key) {
		logger.info("findById()");

		Main.em.getTransaction().begin();
		Worker worker = Main.em.find(Worker.class, key);
		Main.em.getTransaction().commit();
		return worker;

	}

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
