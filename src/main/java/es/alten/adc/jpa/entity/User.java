package es.alten.adc.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username")
	private String userName;
	
	// Relationship via FK
	@OneToOne(cascade = CascadeType.ALL)
	// JoinColumn only in the "owner" of the relationship.  Whoever owns the FK column receives the JoinColumn annotation.
	// References both local column (name) and referenced column (referenceColumnName)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	// Relationship via sharing PK
	// The relationship is mapped in the target entity UserDetails, under the attribute "user"
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private UserDetails userDetails;
	
	// Relationship via Join Table
	// The relationship is mapped via an intermediate table
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "user_workstation", 
		joinColumns = {@JoinColumn(name = "employee_id", referencedColumnName = "id")}, 
		inverseJoinColumns = {@JoinColumn(name = "workstation_id", referencedColumnName = "id")}
	)
	private Workstation workstation;
}
