package es.alten.adc.jpa.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import es.alten.adc.jpa.BaseTest;
import es.alten.adc.jpa.entity.Person;
import es.alten.adc.jpa.entity.PersonName;

public class TestConverter extends BaseTest{

	@Test
	public void givenPersonName_whenSaving_thenNameAndSurnameConcat() {
		
		// Given person with personName
	    String name = "name";
	    String surname = "surname";

	    PersonName personName = new PersonName();
	    personName.setName(name);
	    personName.setSurname(surname);

	    Person person = new Person();
	    person.setPersonName(personName);

	    // When saving
	    entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
	    Long id = (Long) person.getId();

	    // Then name and surname concat
	    String dbPersonName = (String) entityManager.createNativeQuery(
	    	      "select p.personName from PersonTable p where p.id = :id")
	    	      .setParameter("id", id)
	    	      .getSingleResult();

	    assertEquals(surname + ", " + name, dbPersonName);
	    
	    // And whole person name properly converted
	    Person dbPerson = (Person)entityManager.createNativeQuery(
	    	      "select * from PersonTable p where p.id = :id", Person.class)
	    	        .setParameter("id", id)
	    	        .getSingleResult();
	    assertNotNull("Retrieved person is null", dbPerson);
	    assertEquals(dbPerson.getPersonName().getName(), name);
	    assertEquals(dbPerson.getPersonName().getSurname(), surname);

	}
}
