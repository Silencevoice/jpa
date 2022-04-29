package es.alten.adc.jpa.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "items")
@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cart_id")
	private Long id;
	
	@OneToMany(mappedBy = "cart")
	private List<Item> items;
	
	/**
	 * Add item to cart, assiging bidirectional relationship
	 * @param item
	 */
	public void addItem(final Item item) {
		if(items == null) {
			items = new ArrayList<Item>();
		}
		items.add(item);
		item.setCart(this);
	}

}
