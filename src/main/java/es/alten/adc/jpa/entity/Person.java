package es.alten.adc.jpa.entity;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.alten.adc.jpa.converter.PersonNameConverter;
import lombok.Data;

@Data
@Entity(name = "personTable")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Convert(converter = PersonNameConverter.class)
	private PersonName personName;
}
