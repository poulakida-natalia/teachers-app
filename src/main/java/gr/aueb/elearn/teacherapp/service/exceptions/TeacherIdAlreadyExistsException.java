package gr.aueb.elearn.teacherapp.service.exceptions;
import gr.aueb.elearn.teacherapp.model.Teacher;

public class TeacherIdAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeacherIdAlreadyExistsException(Teacher teacher) {
		super("Υπάρχει ήδη εκπαιδευτικός με αναγνωριστικό " + teacher.getId() + ".");
	}

}