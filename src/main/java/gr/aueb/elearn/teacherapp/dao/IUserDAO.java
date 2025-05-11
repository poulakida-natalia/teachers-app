package gr.aueb.elearn.teacherapp.dao;

import java.sql.SQLException;

import gr.aueb.elearn.teacherapp.model.User;
import gr.aueb.elearn.teacherapp.service.exceptions.*;

public interface IUserDAO {
    User login(String username, String password) throws SQLException, UserNotFoundException;
    void register(String username, String password) throws UserAlreadyExistsException, SQLException;
    User getUserByUsername(String username) throws SQLException;
}
