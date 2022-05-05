package es.alten.adc.jpa.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class PersonName implements Serializable{
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -6945532250859782600L;

	private String name;
	
	private String surname;
}
