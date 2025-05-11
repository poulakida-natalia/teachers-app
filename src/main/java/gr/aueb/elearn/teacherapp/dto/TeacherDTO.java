package gr.aueb.elearn.teacherapp.dto;

public class TeacherDTO {
	private int id;
	private String fname;
	private String sname;
	
	public TeacherDTO() {}
	
	public TeacherDTO(int id, String fname, String lname) {
		super();
		this.id = id;
		this.fname = fname;
		this.sname = lname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}
