package gr.aueb.elearn.teacherapp;

import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/searchToModifyController")
public class SearchToModifyController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String searching = request.getParameter("search-id");
        ITeacherDAO teacherDAO = new TeacherDAOImpl();

        try {
            int id = Integer.parseInt(searching);
            Teacher teacher = teacherDAO.getTeacherById(id);

            if (teacher != null) {
                String json = String.format(
                        "{\"fname\": \"%s\", \"sname\": \"%s\"}",
                        teacher.getFname().replace("\"", "\\\""),
                        teacher.getSname().replace("\"", "\\\"")
                );
                response.getWriter().write(json);
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\": \"Δε βρέθηκε εκπαιδευτικός με αυτό το αναγνωριστικό.\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Κάτι πήγε στραβά.\"}");
        }
    }
}
