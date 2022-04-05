package es.alten.adc.jpa;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

public class TestPersistenceContext {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-training");
	
	private EntityManager entityManager;
	
	@Before
	public void setup() {
		entityManager = emf.createEntityManager();
	}

	@Test
	public void givenEntityManagerFactory_whenCreateEntityManager_thenNotNull() {
		
		assertNotNull("entityManager is null", entityManager);
	}
}
