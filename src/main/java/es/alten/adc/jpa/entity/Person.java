package es.alten.adc.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import es.alten.adc.jpa.converter.PersonNameConverter;
import lombok.Data;

@Data
@Entity
// Unique multicolumn constraints
@Table(uniqueConstraints = {
		@UniqueConstraint(name = "UniqueNumberAndStatus",  columnNames = {"personNumber", "active"}), 
		@UniqueConstraint(name = "UniqueSecurityAndDepartment", columnNames = {"securityNumber", "departmentCode"})})
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Convert(converter = PersonNameConverter.class)
	private PersonName personName;
	
    private String password;
    
    // Unique column constraint
    @Column(unique = true)
    private String email;
    
    private Long personNumber;
    private Boolean active;
    private String securityNumber;
    private String departmentCode;
}
