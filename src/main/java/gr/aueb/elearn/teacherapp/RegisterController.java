package gr.aueb.elearn.teacherapp;

import gr.aueb.elearn.teacherapp.dao.IUserDAO;
import gr.aueb.elearn.teacherapp.dao.UserDAOImpl;
import gr.aueb.elearn.teacherapp.dto.UserDTO;
import gr.aueb.elearn.teacherapp.service.IUserService;
import gr.aueb.elearn.teacherapp.service.UserServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.UserAlreadyExistsException;
import gr.aueb.elearn.teacherapp.service.exceptions.WrongPasswordException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registerController")
public class RegisterController extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, WrongPasswordException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username-register");
        String password = request.getParameter("password-register");
        String confirmPassword = request.getParameter("confirm-password");

        try {

            if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Ο κωδικός πρόσβασης και η επαλήθευσή του δεν ταιριάζουν.");
            }

            if (password.length() < 8 || !password.matches(".*[A-Z].*") ||
                    !password.matches(".*[a-z].*") ||
                    !password.matches(".*\\d.*") ||
                    !password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                throw new WrongPasswordException("Ο κωδικός πρόσβασης πρέπει να αποτελείται από τουλάχιστον 8 χαρακτήρες, να περιέχει κεφαλαία και πεζά γράμματα, έναν αριθμό και έναν ειδικό χαρακτήρα.");
            }

            UserDTO userDTO = new UserDTO(username, password);
            IUserDAO userDAO = new UserDAOImpl();
            IUserService userService = new UserServiceImpl(userDAO);

            userService.registerUser(userDTO);

            request.setAttribute("status", "success");
            request.setAttribute("message", "Η εγγραφή του χρήστη " + userDTO.getUsername() + " ολοκληρώθηκε επιτυχώς.");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (UserAlreadyExistsException | WrongPasswordException e1) {
            request.setAttribute("status", "fail");
            request.setAttribute("message", e1.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e3) {
            e3.printStackTrace();
            request.setAttribute("status", "fail");
            request.setAttribute("message", "Κάτι πήγε στραβά.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}
