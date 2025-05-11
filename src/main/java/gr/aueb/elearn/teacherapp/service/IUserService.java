package gr.aueb.elearn.teacherapp.service;

import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.model.User;
import gr.aueb.elearn.teacherapp.service.exceptions.UserAlreadyExistsException;
import gr.aueb.elearn.teacherapp.service.exceptions.UserNotFoundException;
import gr.aueb.elearn.teacherapp.service.exceptions.WrongPasswordException;

import java.sql.SQLException;

public interface IUserService {
    void loginUser(UserDTO userDTO) throws UserAlreadyExistsException, WrongPasswordException, SQLException;
    void registerUser(UserDTO userDTO) throws UserAlreadyExistsException, WrongPasswordException, SQLException;
}
