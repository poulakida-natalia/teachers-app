package gr.aueb.elearn.teacherapp.service.exceptions;
import gr.aueb.elearn.teacherapp.model.Teacher;

public class TeacherNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TeacherNotFoundException(Teacher teacher) {
		super("Δεν υπάρχει χρήστης με αναγνωριστικό " + teacher.getId() + ".");
	}

}
