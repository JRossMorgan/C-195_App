package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointment;
import model.Contacts;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
    public TextField updateTitle;
    public TextField updateDescription;
    public TextField updateLocation;
    public TextField updateType;
    public TextField updateCustomer;
    public TextField updateUser;
    public TextField updateId;
    public ComboBox <Contacts> updateContact;
    public ComboBox <LocalTime> updateStart;
    public ComboBox <LocalTime> updateEnd;
    public DatePicker updateDate;

    public void updateAppointment(Appointment appointmentToUpdate){
        updateId.setText(String.valueOf(appointmentToUpdate.getAppointmentId()));
        updateTitle.setText(appointmentToUpdate.getTitle());
        updateDescription.setText(appointmentToUpdate.getDescription());
        updateLocation.setText(appointmentToUpdate.getLocation());
        updateType.setText(appointmentToUpdate.getType());
        updateCustomer.setText(String.valueOf(appointmentToUpdate.getCustomerId()));
        updateUser.setText(String.valueOf(appointmentToUpdate.getUserId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
