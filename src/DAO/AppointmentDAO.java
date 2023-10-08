package DAO;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDAO {
    public static ObservableList<Appointment> getAppointments() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Contact_Name, Start, End, Customer_ID, User_ID FROM appointments, contacts WHERE appointments.Contact_ID = contacts.Contact_ID";
        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String contact = rs.getString("Contact_Name");
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                Appointment createAppointment = new Appointment(appointmentId, title, description, location, type, contact, start, end, customerId, userId);
                allAppointments.add(createAppointment);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allAppointments;
    }
}

