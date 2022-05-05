package es.alten.adc.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails {

	@Id
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	// This relationship goes back to User.  The mapping details are to share the PK	
	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;
}
