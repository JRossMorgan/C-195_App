package controller;

import DAO.ContactsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public TextField addTitle;
    public TextField addDescription;
    public TextField addLocation;
    public TextField addType;
    public TextField addCustomer;
    public TextField addUser;
    public ComboBox <Contacts> addContact;
    public ComboBox <LocalTime> addStart;
    public ComboBox <LocalTime> addEnd;
    public DatePicker addDate;
    public Button addSave;
    public Button addCancel;
    public DialogPane addAppDialog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addContact.setItems(ContactsDAO.getAllContacts());

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
        try{
            customerID = Integer.parseInt(addCustomer.getText());
        }
        catch (NumberFormatException e){
            addAppDialog.setContentText("Please Enter a Customer ID Number");
            return;
        }

        int userId = 0;
        try{
            userId = Integer.parseInt(addUser.getText());
        }
        catch (NumberFormatException e){
            addAppDialog.setContentText("Please Enter a User ID Number");
            return;
        }

    }

    public void onAddCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
