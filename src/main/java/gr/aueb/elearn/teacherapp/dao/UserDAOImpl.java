package gr.aueb.elearn.teacherapp.dao;

import gr.aueb.elearn.teacherapp.model.Teacher;
import gr.aueb.elearn.teacherapp.model.User;
import gr.aueb.elearn.teacherapp.service.exceptions.UserAlreadyExistsException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static gr.aueb.elearn.teacherapp.dao.dbutil.DBUtil.closeConnection;
import static gr.aueb.elearn.teacherapp.dao.dbutil.DBUtil.openConnection;

public class UserDAOImpl implements IUserDAO {

    @Override
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE USERNAME = '" + username + "'";
        PreparedStatement pst = openConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            return user;
        }

        pst.close();
        closeConnection();
        return null;
    }

    @Override
    public void register(String username, String password) throws UserAlreadyExistsException, SQLException {

        String sql = "INSERT INTO USERS (USERNAME, PASSWORD) VALUES ('" + username + "', '" + password + "')";

        PreparedStatement pst = openConnection().prepareStatement(sql);
        int n = pst.executeUpdate();

        System.out.println(n + " User registered: " + username);

        pst.close();
        closeConnection();
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE USERNAME = '"
                + username + "';";

        PreparedStatement pst =  openConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);

        User user = new User();
        if (rs.next()) {
            user.setUsername(rs.getString("USERNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            return user;
        }

        pst.close();
        closeConnection();
        return null;
    }

}
