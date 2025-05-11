package gr.aueb.elearn.teacherapp;

import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.ITeacherService;
import gr.aueb.elearn.teacherapp.service.TeacherServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.TeacherNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteController")
public class DeleteController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String inId = request.getParameter("de_lid");
        System.out.println(inId);

        try {
            int id = Integer.parseInt(inId);
            TeacherDTO teacherDTO = new TeacherDTO(id, null, null);
            ITeacherDAO teacherDAO = new TeacherDAOImpl();
            ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

            teacherService.deleteTeacher(teacherDTO);

            // Prepare JSON response for success
            String jsonResponse = String.format(
                    "{\"status\": \"delete-success\", \"message\": \"Ο εκπαιδευτικός με αναγνωριστικό %d διαγράφηκε επιτυχώς.\"}",
                    teacherDTO.getId()
            );
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        } catch (TeacherNotFoundException e1) {
            // Prepare JSON response for failure
            String jsonResponse = String.format(
                    "{\"status\": \"delete-fail\", \"message\": \"%s\"}",
                    e1.getMessage()
            );
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        } catch (SQLException | NumberFormatException | NullPointerException e2) {
            e2.printStackTrace();
            String jsonResponse = "{\"status\": \"delete-fail\", \"message\": \"Κάτι πήγε στραβά.\"}";
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        }
    }
}
