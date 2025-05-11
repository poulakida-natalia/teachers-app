package gr.aueb.elearn.teacherapp;

import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.dto.TeacherDTO;
import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.service.ITeacherService;
import gr.aueb.elearn.teacherapp.service.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchController")
public class SearchController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String searching = request.getParameter("search");
        ITeacherDAO teacherDAO = new TeacherDAOImpl();

        try {
            if (searching.matches("[\\p{IsGreek}a-zA-Z]+")) {
                List<Teacher> teachers = teacherDAO.getTeachersBySurname(searching);
                if (teachers == null || teachers.isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Δε βρέθηκε εκπαιδευτικός με αυτό το επώνυμο.\"}");
                } else {
                    StringBuilder jsonBuilder = new StringBuilder();
                    jsonBuilder.append("[");

                    for (int i = 0; i < teachers.size(); i++) {
                        Teacher t = teachers.get(i);
                        jsonBuilder.append(String.format(
                                "{\"id\": %d, \"fname\": \"%s\", \"sname\": \"%s\"}",
                                t.getId(),
                                t.getFname().replace("\"", "\\\""),
                                t.getSname().replace("\"", "\\\"")
                        ));
                        if (i < teachers.size() - 1) jsonBuilder.append(",");
                    }

                    jsonBuilder.append("]");
                    response.getWriter().write(jsonBuilder.toString());
                }
            } else if (searching.matches("\\d+")) {
                int id = Integer.parseInt(searching);
                Teacher teacher = teacherDAO.getTeacherById(id);

                if (teacher != null) {
                    String json = String.format(
                            "{\"id\": %d, \"fname\": \"%s\", \"sname\": \"%s\"}",
                            teacher.getId(),
                            teacher.getFname().replace("\"", "\\\""),
                            teacher.getSname().replace("\"", "\\\"")
                    );
                    response.getWriter().write(json);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\": \"Δε βρέθηκε εκπαιδευτικός με αυτό το αναγνωριστικό.\"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Παρακαλώ συμπληρώστε έγκυρο Αναγνωριστικό ή Επώνυμο.\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\": \"Κάτι πήγε στραβά.\"}");
        }
    }
}
