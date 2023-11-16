package dao;

import jakarta.persistence.EntityManager;
import models.Department;

public class DepartmentDao {

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
}
