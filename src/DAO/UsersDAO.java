package DAO;

/**
 * UsersDAO class UsersDAO.java
 */
/**
 *
 * @author Jedediah R Morgan
 */

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {

    /**
     @return queries the database and returns users in a list */
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        String sql = "SELECT User_ID, User_Name, Password FROM users";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users createUser = new Users(userId, userName, password);
                allUsers.add(createUser);
            }
        }

            catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allUsers;


    }
}
