package gr.aueb.elearn.teacherapp;

import gr.aueb.elearn.teacherapp.dao.ITeacherDAO;
import gr.aueb.elearn.teacherapp.dao.TeacherDAOImpl;
import gr.aueb.elearn.teacherapp.model.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/modifyController")
public class ModifyController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String mId = request.getParameter("search-id");
        String mFname = request.getParameter("fname");
        String mSname = request.getParameter("sname");

        try {
            int id = Integer.parseInt(mId);
            ITeacherDAO teacherDAO = new TeacherDAOImpl();
            Teacher oldTeacher = teacherDAO.getTeacherById(id);
            Teacher newTeacher = new Teacher(id, mFname, mSname);
            teacherDAO.update(oldTeacher, newTeacher);

            String jsonResponse = String.format(
                    "{\"status\": \"modify-success\", \"message\": \"Τα στοιχεία του εκπαιδευτικού με αναγνωριστικό %d ενημερώθηκαν.\"}",
                    id
            );
            response.getWriter().write(jsonResponse);

        } catch (SQLException | NumberFormatException e) {
            String jsonResponse = "{\"status\": \"modify-fail\", \"message\": \"Κάτι πήγε στραβά.\"}";
            response.getWriter().write(jsonResponse);
        }
    }
}
