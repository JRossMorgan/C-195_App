package model;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {

    private String userName;
    private String password;
    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    public Users(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static void userQuery() throws SQLException {
        String sql = "SELECT User_Name, Password FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            Users createUser = new Users(userName, password);
            allUsers.add(createUser);
        }
    }
}
