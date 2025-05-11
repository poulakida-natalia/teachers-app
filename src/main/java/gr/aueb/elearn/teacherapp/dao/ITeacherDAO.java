package gr.aueb.elearn.teacherapp.dao;

import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.exceptions.TeacherIdAlreadyExistsException;
import gr.aueb.elearn.teacherapp.service.exceptions.TeacherNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface ITeacherDAO {
	void insert(Teacher teacher) throws SQLException, TeacherIdAlreadyExistsException;
	void delete(Teacher teacher) throws SQLException, TeacherNotFoundException;
	void update(Teacher oldTeacher, Teacher newTeacher) throws SQLException;
	List<Teacher> getTeachersBySurname(String surname) throws SQLException;
	Teacher getTeacherById(int id) throws SQLException;
}
