package es.alten.adc.jpa.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import es.alten.adc.jpa.BaseTest;
import es.alten.adc.jpa.entity.Company;
import es.alten.adc.jpa.entity.ContactPerson;

public class TestEmbedded extends BaseTest{

	@Test
	public void givenNewCompany_whenSave_thenEquals() {
		// Given a new Company
		final Company company = new Company();
		company.setAddress("1600 Pennsylvania Av. DC");
		company.setName("The White House");
		company.setPhone("202-456-1111");
		final ContactPerson contact = new ContactPerson();
		contact.setFirstName("Joe");
		contact.setLastName("Biden");
		contact.setPhone("N/A");
		company.setContactPerson(contact);
		
		// When save
		entityManager.getTransaction().begin();
		entityManager.persist(company);
		entityManager.getTransaction().commit();
		
		// Then equals
		assertNotNull("CompanyId is null", company.getId());
		final Company foundCompany = entityManager.find(Company.class, company.getId());
		assertEquals("Found company is not equals", company, foundCompany);
	}
}
