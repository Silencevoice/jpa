package es.alten.adc.jpa.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    private String name;

    private String address;

    private String phone;
    
    @Embedded
    private ContactPerson contactPerson;
}
