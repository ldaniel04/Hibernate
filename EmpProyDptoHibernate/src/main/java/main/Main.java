package main;

import controller.MainController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {

	public static EntityManager em = open();

	public static void main(String[] args) {

		new MainController();
	}

	public static EntityManager open() {
		Persistence.generateSchema("unidad-persistencia", null);

		if (em == null) {
			em = Persistence.createEntityManagerFactory("unidad-persistencia").createEntityManager();
		}

		return em;
	}
}
