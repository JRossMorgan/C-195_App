package DAO;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsDAO {
    public static ObservableList<Contacts> getAllContacts(){
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        String sql = "SELECT Contact_Name, Email FROM contacts";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contacts createContacts = new Contacts(contactName, email);
                allContacts.add(createContacts);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allContacts;
    }
}
