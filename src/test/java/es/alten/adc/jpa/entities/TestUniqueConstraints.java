package es.alten.adc.jpa.entities;

import static org.junit.Assert.assertNotNull;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import es.alten.adc.jpa.BaseTest;
import es.alten.adc.jpa.entity.Person;
import es.alten.adc.jpa.entity.PersonName;

public class TestUniqueConstraints extends BaseTest{
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
	private static final String REPEATED_EMAIL = "me@mysite.com";
	
	private static final String SINGLE_EMAIL = "me_single@mysite.com";
	
	private static final String REPEATED_SECURITY = "1234";
	
	private static final String SINGLE_SECURITY = "9876";
	
	private static final String REPEATED_DEPARTMENT = "Sales";
	
	private static final String SINGLE_DEPARTMENT = "Management";
	
	private Person p1 = new Person();
	
	private Person p2 = new Person();
	
	private Person p3 = new Person();
	
	private Person p4 = new Person();
	
	@Before
	public void setup() {
		p1.setActive(true);
		p1.setDepartmentCode("DEPCODE");
		p1.setEmail(REPEATED_EMAIL);
		final PersonName pn1 = new PersonName();
		pn1.setName("Ana");
		pn1.setName("Abrantes");
		p1.setPersonName(pn1);
		p1.setPersonNumber(1L);
		
		p2.setActive(true);
		p2.setDepartmentCode("DEPCODE");
		p2.setEmail(REPEATED_EMAIL);
		final PersonName pn2 = new PersonName();
		pn1.setName("Blas");
		pn1.setName("Bélmez");
		p2.setPersonName(pn2);
		p2.setPersonNumber(2L);
		
		p3.setActive(true);
		p3.setDepartmentCode("DEPCODE");
		p3.setEmail(SINGLE_EMAIL);
		final PersonName pn3 = new PersonName();
		pn1.setName("Carlos");
		pn1.setName("Cernuda");
		p3.setPersonName(pn3);
		p3.setPersonNumber(2L);
		p3.setSecurityNumber(REPEATED_SECURITY);
		p3.setDepartmentCode(REPEATED_DEPARTMENT);
		
		p4.setActive(true);
		p4.setDepartmentCode("DEPCODE");
		p4.setEmail(REPEATED_EMAIL);
		final PersonName pn4 = new PersonName();
		pn1.setName("Daniela");
		pn1.setName("Desantos");
		p4.setPersonName(pn4);
		p4.setPersonNumber(3L);
		p4.setSecurityNumber(REPEATED_SECURITY);
		p4.setDepartmentCode(REPEATED_DEPARTMENT);
	}
	
	@Test
	public void givenTwoPersonsWithSameEmail_WhenPersist_ThenConstraintViolation() {
		// Given two persons with the same email
		
		// Then constraint violation
		thrown.expect(RollbackException.class);
		thrown.expectCause(CoreMatchers.isA(PersistenceException.class));
		
		// When Persist
		entityManager.getTransaction().begin();
		entityManager.persist(p1);
		entityManager.persist(p2);
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void givenTwoPersonsWithSamePersonNumberAndStatus_WhenPersist_ThenConstraintViolation() {
		// Given two persons with the same person number and status
		
		// Then constraint violation
		thrown.expect(RollbackException.class);
		thrown.expectCause(CoreMatchers.isA(PersistenceException.class));
		
		// When Persist
		entityManager.getTransaction().begin();
		entityManager.persist(p2);
		entityManager.persist(p3);
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void givenTwoPersonsWithSameSecurityAndDepartment_WhenPersist_ThenConstraintViolation() {
		// Given two persons with the same security and department
		
		// Then constraint violation
		thrown.expect(RollbackException.class);
		thrown.expectCause(CoreMatchers.isA(PersistenceException.class));
		
		// When Persist
		entityManager.getTransaction().begin();
		entityManager.persist(p3);
		entityManager.persist(p4);
		entityManager.getTransaction().commit();
	}
	
	@Test
	public void givenTwoPersonsWithSameSecurityAndDifferentDepartment_WhenPersist_ThenOK() {
		// Given two persons with the same security and different department
		p3.setDepartmentCode(SINGLE_DEPARTMENT);
		
		// When Persist
		entityManager.getTransaction().begin();
		entityManager.persist(p3);
		entityManager.persist(p4);
		entityManager.getTransaction().commit();
		
		// Then OK
		assertNotNull("The person Id is null", p3.getId());
		assertNotNull("The person Id is null", p4.getId());
		
	}
	
	@Test
	public void givenTwoPersonsWithSameDepartmentAndDifferentSecurity_WhenPersist_ThenOK() {
		// Given two persons with the same security and different department
		p3.setSecurityNumber(SINGLE_SECURITY);
		
		// When Persist
		entityManager.getTransaction().begin();
		entityManager.persist(p3);
		entityManager.persist(p4);
		entityManager.getTransaction().commit();
		
		// Then OK
		assertNotNull("The person Id is null", p3.getId());
		assertNotNull("The person Id is null", p4.getId());
		
	}
}
