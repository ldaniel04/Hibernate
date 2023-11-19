package main;

import controller.MainController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Main {

	public static EntityManager em = open();

	public static void main(String[] args) {

		// Need to create interface CRUD as pro has in his hibernate's project??????
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
