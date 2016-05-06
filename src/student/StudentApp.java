package student;

import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Display reminders of students having a birthday soon.
 *
 * @author you
 */
public class StudentApp {

	/**
	 * Print the names (and birthdays) of students having a birtday in the
	 * specified month.
	 *
	 * @param students
	 * 		all students in program
	 * @param filter
	 * 		filter students
	 * @param action
	 * 		action to do something (example print)
	 * @param by
	 * 		sort output
	 */
	public void filterAndPrint(List<Student> students, Predicate<Student> filter, Consumer<Student> action, Comparator<Student> by) {
		students.stream().filter(filter).sorted(by).forEach(action);
	}
	
	public static void main(String[] args) {
		// month to filter
		Month month = Month.MAY;

		List<Student> students = Registrar.getInstance().getStudents();
		StudentApp app = new StudentApp();

		// filter the collection
		Predicate<Student> predicate = s -> s.getBirthdate().getMonth().equals(month);

		// action
		Consumer<Student> consumer = s -> System.out.println(s.toString());

		// sort the collection
		Comparator<Student> byFirstName = (s1, s2) -> s1.getFirstname().compareTo(s2.getFirstname());
		Comparator<Student> byLastName = (s1, s2) -> s1.getLastname().compareTo(s2.getLastname());
		Comparator<Student> byBirthDate = (s1, s2) -> s1.getBirthdate().compareTo(s2.getBirthdate());
		Comparator<Student> byID = (s1, s2) -> s1.getId().compareTo(s2.getId());

		app.filterAndPrint(students, predicate, consumer, byLastName);
	}
}
