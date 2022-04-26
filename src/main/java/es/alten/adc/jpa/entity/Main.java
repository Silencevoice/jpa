package es.alten.adc.jpa.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-training");
		EntityManager entityManager = emf.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.getTransaction().commit();
	}
}
