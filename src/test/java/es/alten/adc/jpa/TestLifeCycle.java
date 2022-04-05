package es.alten.adc.jpa;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import es.alten.adc.jpa.entity.Gender;
import es.alten.adc.jpa.entity.Student;

/**
 * Test {@link Student} callback lifecycle methods
 * @author jaime.alvarez
 *
 */
public class TestLifeCycle extends BaseTest{

	@Test
	/**
	 * Cannot verify logging. Visual inspection required
	 */
	public void testCRUD() {
		// Create a new Student
		final Student student = new Student();
		student.setName("Agnes Alcántara");
		student.setGender(Gender.FEMALE);
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DATE, 1);
		student.setBirthDate(calendar.getTime());
		
		// Create
		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		
		// Update
		Student foundStudent = entityManager.find(Student.class, student.getId());
		foundStudent.setName(foundStudent.getName() + " mod");
		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		
		// Delete
		foundStudent = entityManager.find(Student.class, student.getId());
		entityManager.getTransaction().begin();
		entityManager.remove(foundStudent);
		entityManager.getTransaction().commit();
		
	}
}
