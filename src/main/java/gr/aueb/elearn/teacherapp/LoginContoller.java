package gr.aueb.elearn.teacherapp;

import gr.aueb.elearn.teacherapp.dao.IUserDAO;
import gr.aueb.elearn.teacherapp.dao.UserDAOImpl;
import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.service.IUserService;
import gr.aueb.elearn.teacherapp.service.UserServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.UserNotFoundException;
import gr.aueb.elearn.teacherapp.service.exceptions.WrongPasswordException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginController")
public class LoginContoller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, WrongPasswordException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            UserDTO userDTO = new UserDTO(username, password);
            IUserDAO userDAO = new UserDAOImpl();
            IUserService userService = new UserServiceImpl(userDAO);

            userService.loginUser(userDTO);

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", userDTO.getUsername());

            request.setAttribute("status", "success");
            request.setAttribute("message", "Η εγγραφή του χρήστη " + userDTO.getUsername() + " ολοκληρώθηκε επιτυχώς.");
            request.getRequestDispatcher("menu.jsp").forward(request, response);

        } catch (UserNotFoundException | WrongPasswordException e1) {
            e1.printStackTrace();
            request.setAttribute("status", "fail");
            request.setAttribute("message", e1.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e2) {
            e2.printStackTrace();
            request.setAttribute("status", "fail");
            request.setAttribute("message", "Κάτι πήγε στραβά.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}
