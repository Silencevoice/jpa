package es.alten.adc.jpa.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import es.alten.adc.jpa.BaseTest;
import es.alten.adc.jpa.entity.Cart;
import es.alten.adc.jpa.entity.Item;

public class TestOneToMany extends BaseTest{
	
	private Cart cart = new Cart();
	
	private Item item1 = new Item();
	
	private Item item2 = new Item();

	@Before
	public void setup() {
		item1.setName("Item 1");
		item1.setPrice(1.01);
		
		item2.setName("Item 2");
		item2.setPrice(2.02);
		
		cart.addItem(item1);
		cart.addItem(item2);
	}
	
	@Test
	public void givenCartItems_whenSave_ThenOK() {
		// Given cart with items
//		final List<Item> items = new ArrayList<Item>();
//		items.add(item1);
//		items.add(item2);
//		cart.setItems(items);
//		item1.setCart(cart);
//		item2.setCart(cart);
		
		// When save
		entityManager.getTransaction().begin();
		entityManager.persist(cart);
		entityManager.persist(item1);
		entityManager.persist(item2);
		entityManager.getTransaction().commit();
		
		// Then the cart and the items exist and are related
		final Long cartId = cart.getId();
		assertNotNull("Cart ID is null", cartId);
		final Cart loaded =  entityManager.createQuery("from Cart c where c.id = ?1", Cart.class)
				.setParameter(1, cartId)
				.getSingleResult();
		
		assertNotNull("Loaded cart is null", loaded);
		assertEquals("Loaded cart is not equals", cart, loaded);
		
		final List<Item> loadedItems = loaded.getItems();
		assertNotNull("Items are null", loadedItems);
		assertEquals("Size of items is not equal", cart.getItems().size(), loadedItems.size());
		loadedItems.forEach(i -> assertTrue("loadedItems does not contain item", loadedItems.contains(i)));
	}
	
}
