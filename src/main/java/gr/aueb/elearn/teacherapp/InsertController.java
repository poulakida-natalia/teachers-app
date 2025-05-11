package gr.aueb.elearn.teacherapp;

import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.service.ITeacherService;
import gr.aueb.elearn.teacherapp.service.TeacherServiceImpl;
import gr.aueb.elearn.teacherapp.service.exceptions.TeacherIdAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insertController")
public class InsertController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        String inId = request.getParameter("in_id");
        String inFname = request.getParameter("in_fname");
        String inSname = request.getParameter("in_sname");

        try {
            int id = Integer.parseInt(inId);
            TeacherDTO teacherDTO = new TeacherDTO(id, inFname, inSname);
            ITeacherDAO teacherDAO = new TeacherDAOImpl();
            ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);

            teacherService.insertTeacher(teacherDTO);

            // Success response
            String jsonResponse = String.format(
                    "{\"status\": \"insert-success\", \"message\": \"Ο εκπαιδευτικός με αναγνωριστικό %d αποθηκεύτηκε επιτυχώς.\"}",
                    teacherDTO.getId()
            );
            response.getWriter().write(jsonResponse);
        } catch (TeacherIdAlreadyExistsException e1) {
            // Failure response if ID already exists
            String jsonResponse = String.format(
                    "{\"status\": \"insert-fail\", \"message\": \"%s\"}",
                    e1.getMessage()
            );
            response.getWriter().write(jsonResponse);
        } catch (SQLException | NumberFormatException e2) {
            e2.printStackTrace();
            String jsonResponse = "{\"status\": \"insert-fail\", \"message\": \"Κάτι πήγε στραβά.\"}";
            response.getWriter().write(jsonResponse);
        }
    }
}
