package es.alten.adc.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "workstation")
public class Workstation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "workstation_number")
	private Integer workstationNumber;
	
	private String floor;
	
	// Reverse relationship, mapped by the attribute "workstation" in the target entity User
	@OneToOne(mappedBy = "workstation")
	private User user;
}
