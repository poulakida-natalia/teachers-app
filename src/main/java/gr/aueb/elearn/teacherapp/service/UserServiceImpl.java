package gr.aueb.elearn.teacherapp.service;

import gr.aueb.elearn.teacherapp.dao.IUserDAO;
import gr.aueb.elearn.teacherapp.dao.UserDAOImpl;
import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.model.User;
import gr.aueb.elearn.teacherapp.service.exceptions.UserAlreadyExistsException;
import gr.aueb.elearn.teacherapp.service.exceptions.UserNotFoundException;
import gr.aueb.elearn.teacherapp.service.exceptions.WrongPasswordException;

import java.sql.SQLException;

public class UserServiceImpl implements IUserService{


    private final IUserDAO userDAO;
    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(UserDTO userDTO)
            throws UserAlreadyExistsException, SQLException {

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());

        if ((userDAO.getUserByUsername(newUser.getUsername())) == null)
            userDAO.register(newUser.getUsername(), userDTO.getPassword());
        else {
            throw new UserAlreadyExistsException("Υπάρχει ήδη χρήστης με αυτά τα στοιχεία.");
        }
    }

    public void loginUser(UserDTO userDTO) throws UserAlreadyExistsException, WrongPasswordException, SQLException {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());


        if (userDAO.getUserByUsername(user.getUsername()) == null) {
            System.out.println(user.toString());
            System.out.println(userDAO.getUserByUsername(user.getUsername()));
            throw new UserNotFoundException("Δε βρέθηκε χρήστης με αυτά τα στοιχεία.");
        } else {
            if (!userDAO.getUserByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
                throw new WrongPasswordException("Λάθος κωδικός πρόσβασης.");
            } else {
                userDAO.login(user.getUsername(), user.getPassword());
            }
        }
    }

}
