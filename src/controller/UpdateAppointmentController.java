package controller;

import DAO.ContactsDAO;
import DAO.CustomersDAO;
import DAO.UsersDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contacts;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {
    public TextField updateTitle;
    public TextField updateDescription;
    public TextField updateLocation;
    public TextField updateType;
    public ComboBox <Customers> updateCustomer;
    public ComboBox <Users> updateUser;
    public TextField updateId;
    public ComboBox <Contacts> updateContact;
    public ComboBox <LocalTime> updateStart;
    public ComboBox <LocalTime> updateEnd;
    public DatePicker updateDate;
    public Button updateSave;
    public Button updateCancel;
    public DialogPane updateDialog;

    public void updateAppointment(Appointment appointmentToUpdate){
        updateId.setText(String.valueOf(appointmentToUpdate.getAppointmentId()));
        updateTitle.setText(appointmentToUpdate.getTitle());
        updateDescription.setText(appointmentToUpdate.getDescription());
        updateLocation.setText(appointmentToUpdate.getLocation());
        updateType.setText(appointmentToUpdate.getType());
        for(Customers customerToUpdate : CustomersDAO.getAllCustomers()){
            if(customerToUpdate.getCustomerId() == appointmentToUpdate.getCustomerId()){
                updateCustomer.setValue(customerToUpdate);
            }
        }
        for(Users userToUpdate : UsersDAO.getAllUsers()){
            if(userToUpdate.getUserId() == appointmentToUpdate.getUserId()){
                updateUser.setValue(userToUpdate);
            }
        }
        for(Contacts contactToUpdate : ContactsDAO.getAllContacts()){
            if(contactToUpdate.getContactName().contentEquals(appointmentToUpdate.getContact())){
                updateContact.setValue(contactToUpdate);
            }
        }
        updateDate.setValue(appointmentToUpdate.getStartTime().toLocalDate());
        updateStart.setValue(appointmentToUpdate.getStartTime().toLocalTime());
        updateEnd.setValue(appointmentToUpdate.getEndTime().toLocalTime());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateContact.setItems(ContactsDAO.getAllContacts());
        updateCustomer.setItems(CustomersDAO.getAllCustomers());
        updateUser.setItems(UsersDAO.getAllUsers());

    }

    public void onUpdateSave(ActionEvent actionEvent) {
        int updatedId = 0;
        try{
            updatedId = Integer.parseInt(updateId.getText());
        }
        catch(NumberFormatException e){
            updateDialog.setContentText("It's automatically filled, so this shouldn't appear.");
            return;
        }

        String title = updateTitle.getText();
        if(title == null){
            updateDialog.setContentText("Please enter a title.");
            return;
        }

        String description = updateDescription.getText();
        if(description == null){
            updateDialog.setContentText("Please enter a description.");
            return;
        }

        String location = updateLocation.getText();
        if(location == null){
            updateDialog.setContentText("Please enter a location.");
            return;
        }

        String type = updateType.getText();
        if(type == null){
            updateDialog.setContentText("Please enter a type.");
            return;
        }


    }

    public void onUpdateCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSelect(ActionEvent actionEvent) {
        ZonedDateTime s = ZonedDateTime.of(updateDate.getValue(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime e = ZonedDateTime.of(updateDate.getValue(), LocalTime.of(21, 30), ZoneId.of("America/New_York"));

        LocalTime localS = s.toLocalTime();
        LocalTime localE = e.toLocalTime();
        while(localS.isBefore(localE.plusSeconds(1))){
            updateStart.getItems().add(localS);
            localS = localS.plusMinutes(30);
        }

        ZonedDateTime t = ZonedDateTime.of(updateDate.getValue(), LocalTime.of(8, 30), ZoneId.of("America/New_York"));
        ZonedDateTime l = ZonedDateTime.of(updateDate.getValue(), LocalTime.of(22, 0), ZoneId.of("America/New_York"));

        LocalTime localT = t.toLocalTime();
        LocalTime localL = l.toLocalTime();
        while(localT.isBefore(localL.plusSeconds(1))){
            updateEnd.getItems().add(localT);
            localT = localT.plusMinutes(30);
        }
    }
}
