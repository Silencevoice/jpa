package es.alten.adc.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import es.alten.adc.jpa.lifecycle.AuditTrailListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Entity(name = "student")
@Table(name = "STUDENT")
@EntityListeners(AuditTrailListener.class)
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
	
	@PrePersist
	public void logCreatingStudent() {
		log.info("Creating new student: {}", this);
	}
	
	@PostPersist
	public void logCreatedStudent() {
		log.info("Student created: {}", this);
	}
	
	@PreRemove
	public void logDeletingStudent() {
		log.info("Deleting student: {}", this);
	}
	
	@PostRemove
	public void logDeletedStudent() {
		log.info("Deleted student: {}", this);
	}
	
	@PreUpdate
	public void logUpdatingStudent() {
		log.info("Updating student: {}", this);
	}
	
	@PostUpdate
	public void logUpdatedStudent() {
		log.info("Updated student: {}", this);
	}
}
