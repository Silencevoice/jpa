package es.alten.adc.jpa.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

/**
 * Embeddable class when we need a Contact Person.  Attribute names are overriden
 * 
 */
@Embeddable
@Data
@AttributeOverrides({
	@AttributeOverride(name = "firstName", column = @Column(name = "contact_first_name")), 
	@AttributeOverride(name = "lastName", column = @Column(name = "contact_last_name")),
	@AttributeOverride(name = "phone", column = @Column(name = "contact_phone"))
})
public class ContactPerson {
	
	private String firstName;

    private String lastName;

    private String phone;
}
