package es.alten.adc.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity(name = "student")
@Table(name = "STUDENT")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "STUDENT_NAME", length = 50, nullable = false, unique = false)
	private String name;
	
	/**
	 * Age is calculated using the birthDate field
	 */
	@Transient
	private Integer age;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
}
