package main;

import controller.MainController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
/**
 * 
 * @author Jose Antonio Fernandez-Montes Garcia, Jorge Balmisa Rosillo, Luis Daniel Cedeño Murillo 
 * 
 */
public class Main {

	public static EntityManager em = open();

	public static void main(String[] args) {

		new MainController();
		close();
	}

	private static EntityManager open() {
		Persistence.generateSchema("unidad-persistencia", null);

		if (em == null) {
			em = Persistence.createEntityManagerFactory("unidad-persistencia").createEntityManager();
		}

		return em;
	}
	
	private static void close() {
		if (em.isOpen()) {
			em.close();
		}
	}
}
