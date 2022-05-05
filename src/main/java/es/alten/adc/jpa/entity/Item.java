package es.alten.adc.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "cart")
@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;
}
