package controller;

import DAO.AppointmentDAO;
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
import java.sql.Timestamp;
import java.time.*;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public TextField addTitle;
    public TextField addDescription;
    public TextField addLocation;
    public TextField addType;
    public ComboBox <Contacts> addContact;
    public ComboBox <LocalTime> addStart;
    public ComboBox <LocalTime> addEnd;
    public DatePicker addDate;
    public Button addSave;
    public Button addCancel;
    public DialogPane addAppDialog;
    public ComboBox <Customers> addCustomer;
    public ComboBox <Users> addUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /*addContact.setItems(ContactsDAO.getAllContacts());
        addCustomer.setItems(CustomersDAO.getAllCustomers());
        addUser.setItems(UsersDAO.getAllUsers());

        ZonedDateTime s = ZonedDateTime.of(addDate.getValue(), LocalTime.parse("8"), ZoneId.of("EST"));
        ZonedDateTime e = ZonedDateTime.of(addDate.getValue(), LocalTime.parse("22"), ZoneId.of("EST"));
        while(s.isBefore(e.plusSeconds(1))){
            addStart.getItems().add(s.toLocalTime());
            s = s.plusMinutes(30);
        }
        LocalTime q = LocalTime.of(0, 30);
        LocalTime z = LocalTime.of(23, 30);
        while(q.isBefore(z.plusSeconds(1))){
            addEnd.getItems().add(q);
            q = q.plusMinutes(30);
        }*/
        

    }

    public void onAddSave(ActionEvent actionEvent) {

        String title = addTitle.getText();
        if(title.isBlank()){
            addAppDialog.setContentText("Please Enter a Title");
        }

        String description= addDescription.getText();
        if(description.isBlank()){
            addAppDialog.setContentText("Please Enter a Description");
        }

        String location = addLocation.getText();
        if(location.isBlank()){
            addAppDialog.setContentText("Please Enter a Location");
        }

        String type =  addType.getText();
        if(type.isBlank()){
            addAppDialog.setContentText("Please Enter a Type");
        }

        int customerID = 0;
        Customers CU = (Customers) addCustomer.getSelectionModel().getSelectedItem();
        if(CU == null){
            addAppDialog.setContentText("Please Select a Customer");
            return;
        }
        else{
            customerID = CU.getCustomerId();
        }

        int userId = 0;
        Users US = addUser.getSelectionModel().getSelectedItem();
        if(US == null){
            addAppDialog.setContentText("Please Select a User");
            return;
        }
        else{
            userId = US.getUserId();
        }

        String contact;
        Contacts PQ = (Contacts) addContact.getSelectionModel().getSelectedItem();
        if(PQ == null){
            addAppDialog.setContentText("Please Select a Contact");
            return;
        }
        else{
            contact = PQ.getContactName();
        }

        int contactId = 0;
        for(Contacts contacts : ContactsDAO.getAllContacts()){
            if(contacts.getContactName().contentEquals(contact)){
                contactId = contacts.getContactId();
            }
        }

        LocalDate appDate = addDate.getValue();
        if(appDate == null){
            addAppDialog.setContentText("Please Choose a Date");
            return;
        }

        LocalTime SP = addStart.getSelectionModel().getSelectedItem();
        if(SP == null){
            addAppDialog.setContentText("Please Select an Appointment Start Time");
            return;
        }

        LocalTime EZ = addEnd.getSelectionModel().getSelectedItem();
        if(EZ == null){
            addAppDialog.setContentText("Please Select an Appointment end time");
            return;
        }

        LocalDateTime appointmentStart = LocalDateTime.of(appDate, SP);
        LocalDateTime appointmentEnd = LocalDateTime.of(appDate, EZ);

        ZonedDateTime open = ZonedDateTime.of(appDate, LocalTime.parse("8"), ZoneId.of("EST"));
        ZonedDateTime close = ZonedDateTime.of(appDate, LocalTime.parse("22"), ZoneId.of("EST"));

        LocalDateTime startTime;
        if(appointmentStart.isBefore(open.toLocalDateTime()) || appointmentStart.isAfter(close.toLocalDateTime())){
            addAppDialog.setContentText("Please Choose a Start Time Between 8 AM and 10 PM EST");
            return;
        }
        else{
            startTime = appointmentStart;
        }

        LocalDateTime endTime;
        if(appointmentEnd.isBefore(open.toLocalDateTime()) || appointmentEnd.isAfter(close.toLocalDateTime())){
            addAppDialog.setContentText("Please Choose an End Time Between 8 AM and 10 PM EST");
            return;
        }
        else{
            endTime = appointmentEnd;
        }

        AppointmentDAO.insertAppointment(title, description, location, type, Timestamp.valueOf(startTime), Timestamp.valueOf(endTime), customerID, userId, contactId);
    }

    public void onAddCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
