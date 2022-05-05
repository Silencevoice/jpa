package es.alten.adc.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import es.alten.adc.jpa.entity.Gender;
import es.alten.adc.jpa.entity.Student;

/**
 * Tests the persistance context and checks basic functionality
 * @author jaime.alvarez
 *
 */
public class TestPersistenceContext extends BaseTest{


	@Before
	public void setup() {
		entityManager = emf.createEntityManager();
	}

	@Test
	public void givenEntityManagerFactory_whenCreateEntityManager_thenNotNull() {
		
		assertNotNull("entityManager is null", entityManager);
	}
	
	@Test
	public void givenNewStudent_whenSave_thenFound() {
		// Given new Student
		final Student student = new Student();
		student.setName("Agnes Alcántara");
		student.setGender(Gender.FEMALE);
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		student.setBirthDate(calendar.getTime());
		
		// When save
		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		
		// Then found
		assertNotNull("Persisted student has null Id", student.getId());
		final Student foundStudent = entityManager.find(Student.class, student.getId());
		assertNotNull("Student not found", foundStudent);
		assertEquals("Found student not equals to original", student, foundStudent);
	}
}
