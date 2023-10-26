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
        addContact.setItems(ContactsDAO.getAllContacts());
        addCustomer.setItems(CustomersDAO.getAllCustomers());
        addUser.setItems(UsersDAO.getAllUsers());



        /*LocalTime q = LocalTime.of(0, 30);
        LocalTime z = LocalTime.of(8, 30);
        while(q.isBefore(z.plusSeconds(1))){
            addEnd.getItems().add(q);
            q = q.plusMinutes(30);
        }*/
        

    }

    public void onAddSave(ActionEvent actionEvent) throws IOException{

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
        Customers CU = addCustomer.getSelectionModel().getSelectedItem();
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

        ZonedDateTime open = ZonedDateTime.of(appDate, LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime close = ZonedDateTime.of(appDate, LocalTime.of(22, 0), ZoneId.of("America/New_York"));

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

        for(Appointment eA : AppointmentDAO.getAppointments()){
            if(customerID == eA.getCustomerId()){
                if((startTime.isBefore(eA.getStartTime()) || startTime.isEqual(eA.getStartTime())) && endTime.isAfter(eA.getStartTime())){
                    addAppDialog.setContentText("This Customer Already has an appointment scheduled for this time. Please choose a different time.");
                    return;
                }
                else if((startTime.isAfter(eA.getStartTime()) || startTime.isEqual(eA.getStartTime())) && startTime.isBefore(eA.getEndTime())){
                    addAppDialog.setContentText("This Customer Already has an appointment scheduled for this time. Please choose a different time.");
                    return;
                }
                else if((startTime.isBefore(eA.getStartTime()) || startTime.isEqual(eA.getStartTime())) && (endTime.isAfter(eA.getEndTime()) || endTime.isEqual(eA.getEndTime()))){
                    addAppDialog.setContentText("This Customer Already has an appointment scheduled for this time. Please choose a different time.");
                    return;
                }
                else{
                    AppointmentDAO.insertAppointment(title, description, location, type, Timestamp.valueOf(startTime), Timestamp.valueOf(endTime), customerID, userId, contactId);

                    Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
                    Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }

    public void onAddCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSelect(ActionEvent actionEvent) {
        ZonedDateTime s = ZonedDateTime.of(addDate.getValue(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime e = ZonedDateTime.of(addDate.getValue(), LocalTime.of(21, 30), ZoneId.of("America/New_York"));

        LocalTime localS = s.toLocalTime();
        LocalTime localE = e.toLocalTime();
        while(localS.isBefore(localE.plusSeconds(1))){
            addStart.getItems().add(localS);
            localS = localS.plusMinutes(30);
        }

        ZonedDateTime t = ZonedDateTime.of(addDate.getValue(), LocalTime.of(8, 30), ZoneId.of("America/New_York"));
        ZonedDateTime l = ZonedDateTime.of(addDate.getValue(), LocalTime.of(22, 0), ZoneId.of("America/New_York"));

        LocalTime localT = t.toLocalTime();
        LocalTime localL = l.toLocalTime();
        while(localT.isBefore(localL.plusSeconds(1))){
            addEnd.getItems().add(localT);
            localT = localT.plusMinutes(30);
        }
    }
}
