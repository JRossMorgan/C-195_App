package model;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
    public static ObservableList<Users> getAllUsers() {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        String sql = "SELECT User_Name, Password FROM users";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users createUser = new Users(userName, password);
                allUsers.add(createUser);
            }
        }

            catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allUsers;


    }
}
