package student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A student with a name, student id, and birthday.
 *
 * @author jim
 */
public class Student {
	private String id;
	private String firstname, lastname;
	private LocalDate birthdate;
	
	/**
	 * Initialize attributes of a new student
	 *
	 * @param id
	 * 		student ID
	 * @param firstName
	 * 		first name
	 * @param lastName
	 * 		last name
	 * @param birthday
	 * 		birthday
	 */
	public Student(String id, String firstName, String lastName, String birthday) {
		this.id = id;
		this.firstname = firstName;
		this.lastname = lastName;
		// date should be in the format "dd/MM/yyyy"
		setBirthdate(birthday);
	}

	/**
	 * Get the student's first name.
	 *
	 * @return the first name
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * Get the student's last name.
	 *
	 * @return the last name
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * Get the student's birth date.
	 *
	 * @return the birthdate as a LocalDate instance.
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * Get the student's ID.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * A hacky method for setting the birth date using String.
	 *
	 * @param datestr
	 * 		is a String containing birthday in the format "dd/mm/yyyy".
	 * @throws DateTimeParseException
	 * 		if datestr is not in the correct format.
	 */
	private void setBirthdate(String datestr) {
		birthdate = LocalDate.parse(datestr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	@Override
	public String toString() {
		return firstname + " " + lastname + " will have birthday on " + birthdate.getDayOfMonth() + " " + birthdate.getMonth();
	}
}
