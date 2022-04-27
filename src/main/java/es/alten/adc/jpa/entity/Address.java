package es.alten.adc.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;
	
	// way to map back the relation from Adress to User
	// Since the relationship is already mapped, mappedBy attribute is used, meaning that
	// the relationship details are mapped in the target entity under the attribute name "address"
	@OneToOne(mappedBy = "address")
	private User user;
}
