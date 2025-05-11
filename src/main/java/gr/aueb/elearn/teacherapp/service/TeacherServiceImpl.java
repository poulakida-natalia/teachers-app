package gr.aueb.elearn.teacherapp.service;

import gr.aueb.elearn.teacherapp.dao.*;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.exceptions.TeacherIdAlreadyExistsException;
import gr.aueb.elearn.teacherapp.service.exceptions.TeacherNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements ITeacherService {
	
	private final ITeacherDAO teacherDAO;
	
	public TeacherServiceImpl(ITeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	@Override
	public void insertTeacher(TeacherDTO teacherDTO) 
			throws TeacherIdAlreadyExistsException, SQLException {
		
		Teacher newTeacher = new Teacher();
		newTeacher.setId(teacherDTO.getId());
		newTeacher.setSname(teacherDTO.getSname());
		newTeacher.setFname(teacherDTO.getFname());	
		
		if ((teacherDAO.getTeacherById(newTeacher.getId())) == null) 
			teacherDAO.insert(newTeacher);
		else {
			throw new TeacherIdAlreadyExistsException(newTeacher);
		}
	}

	@Override
	public void deleteTeacher(TeacherDTO teacherDTO) throws TeacherNotFoundException, SQLException {
		Teacher teacherToDelete = new Teacher();
		teacherToDelete.setId(teacherDTO.getId());
		
		if ((teacherDAO.getTeacherById(teacherToDelete.getId())) == null) 
			throw new TeacherNotFoundException(teacherToDelete);
		teacherDAO.delete(teacherToDelete);
		
	}

	@Override
	public void updateTeacher(TeacherDTO oldTeacherDTO, TeacherDTO newTeacherDTO) throws TeacherNotFoundException, SQLException {
		Teacher teacherToUpdate = new Teacher();
		teacherToUpdate.setId(oldTeacherDTO.getId());
		
		Teacher newTeacher = new Teacher();
		newTeacher.setId(newTeacherDTO.getId());
		newTeacher.setSname(newTeacherDTO.getSname());
		newTeacher.setFname(newTeacherDTO.getFname());	
		
		if ((teacherDAO.getTeacherById(teacherToUpdate.getId())) == null) 
			throw new TeacherNotFoundException(teacherToUpdate);
		teacherDAO.update(teacherToUpdate, newTeacher);
		
	}

	@Override
	public List<Teacher> getTeachersBySurname(String surname) throws SQLException {	
		return teacherDAO.getTeachersBySurname(surname);
	}
}
