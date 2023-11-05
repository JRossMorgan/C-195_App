package controller;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public Button customers;
    public Button appointments;

    public ObservableList <Appointment> userAppointments = FXCollections.observableArrayList();
    public DialogPane mainDialog;
    public Button contact;

    public void setUser (Users userToSet){
        int loggedInUser = userToSet.getUserId();

        for(Appointment appointment : AppointmentDAO.getAppointments()){
            if(appointment.getUserId() == loggedInUser){
                userAppointments.add(appointment);
            }
        }

        LocalDateTime logInTime = LocalDateTime.now();
        long timeDifference;
        boolean appointmentAlert = false;
        for(Appointment timedAppointment : userAppointments){
            if(timedAppointment.getStartTime().isAfter(logInTime) && timedAppointment.getStartTime().isBefore(logInTime.plusMinutes(15))){
                appointmentAlert = true;
                timeDifference = ChronoUnit.MINUTES.between(logInTime, timedAppointment.getStartTime());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setContentText("You have an appointment in " + timeDifference + " minute(s)");
                alert.showAndWait();

            }
        }
        if(! appointmentAlert){
            mainDialog.setContentText("No upcoming appointments scheduled.");
        }
    }

    public void goToCustomers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersPage.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    public void onApp(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onContact(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Contacts.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
