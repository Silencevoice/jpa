package es.alten.adc.jpa.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import es.alten.adc.jpa.BaseTest;
import es.alten.adc.jpa.entity.Address;
import es.alten.adc.jpa.entity.User;
import es.alten.adc.jpa.entity.UserDetails;
import es.alten.adc.jpa.entity.Workstation;

public class TestOneToOne extends BaseTest{
	
	private static final String USER_NAME = "username";

	private User user = new User();
	
	private UserDetails userDetails = new UserDetails();
	
	private Address address = new Address();
	
	private Workstation workstation = new Workstation();
	
	@Before
	public void setup() {
		user.setUserName(USER_NAME);
		
		address.setCity("Madrid");
		address.setStreet("Calle Mayor");
		
		userDetails.setFirstName("FirstName");
		userDetails.setLastName("LastName");
		
		workstation.setFloor("First");
		workstation.setWorkstationNumber(1);
		
		// Bind relationships
		user.setAddress(address);
		address.setUser(user);
		
		user.setUserDetails(userDetails);
		userDetails.setUser(user);
		
		user.setWorkstation(workstation);
		workstation.setUser(user);
	}
	
	@Test
	public void givenOneToOneRelationships_whenSavingAndThenLoading_ThenRelationshipsOK() {
		// Given one to one relationships
		
		// when saving
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		final Long userId = user.getId();
		assertNotNull("user ID is null", userId);
		
		// and then loading
		// final User loaded = entityManager.find(User.class, userId);
		final User loaded = entityManager.createQuery("from User u where u.id = ?1", User.class)
				.setParameter(1, userId)
				.getSingleResult();
		
		// Then relationships are OK
		assertNotNull("Loaded user is null", loaded);
		assertNotNull("Associated address is null", loaded.getAddress());
		assertEquals("Address is not equal", address, loaded.getAddress());
		assertNotNull("Associated userDetails is null", loaded.getUserDetails());
		assertEquals("UserDetails is not equal", userDetails, loaded.getUserDetails());
		assertNotNull("Associated workspace is null", loaded.getWorkstation());
		assertEquals("Workspace is not equal", workstation, loaded.getWorkstation());
	}
}
