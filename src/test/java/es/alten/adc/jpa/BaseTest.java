package es.alten.adc.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

/**
 * Base test with common setup
 * 
 * @author jaime.alvarez
 *
 */
public class BaseTest {

	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-training");
	
	protected EntityManager entityManager;

	@Before
	public void setupBase() {
		entityManager = emf.createEntityManager();
	}
	
	@After
	public void tearDownBase() {
		entityManager.close();
	}
}
