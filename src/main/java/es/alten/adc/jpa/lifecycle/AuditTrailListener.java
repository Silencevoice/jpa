package es.alten.adc.jpa.lifecycle;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import es.alten.adc.jpa.entity.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * Audits entity Create, Update and Delete
 * 
 * @author jaime.alvarez
 *
 */
public class AuditTrailListener {
	
	@PrePersist
	@PreUpdate
	@PreRemove
	public void beforeAnyUpdate(Student student) {
		if(student.getId() == null || student.getId() == 0) {
			log.info("[STUDENT AUDIT] - About to add a student: {}", student);
		} else {
			log.info("[STUDENT AUDIT] - About to update/delete student: {}", student);
		}
	}
	
	@PostPersist
	@PostUpdate
	@PostRemove
	public void afterAnyUpdate(Student student) {
		log.info("[STUDENT AUDIT] - add/update/delete completed for student: {}", student);
	}
	
	@PostLoad
	public void afterLoad(Student student) {
		log.info("[STUDENT AUDIT] - student loaded form database: {}", student);
	}
}
