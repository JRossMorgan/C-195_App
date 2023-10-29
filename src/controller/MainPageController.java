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

    public void setUser (Users userToSet){
        int loggedInUser = userToSet.getUserId();

        LocalDateTime logInTime = LocalDateTime.now();
        ZonedDateTime zonedLogIn = ZonedDateTime.of(logInTime.toLocalDate(), logInTime.toLocalTime(), ZoneId.of("GMT"));

        LocalDateTime converted = zonedLogIn.toLocalDateTime();

        for(Appointment appointment : AppointmentDAO.getAppointments()){
            if(appointment.getUserId() == loggedInUser){
                userAppointments.add(appointment);
            }
        }

        Long timeDifference;
        for(Appointment timedAppointment : userAppointments){
            if(ChronoUnit.MINUTES.between(converted, timedAppointment.getStartTime()) <= 15){
                timeDifference = ChronoUnit.MINUTES.between(converted, timedAppointment.getStartTime());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upcoming Appointment");
                alert.setContentText("You have an appointment in " + timeDifference + " minute(s)");
                return;

            }
            else{
                mainDialog.setContentText("You have no upcoming appointments.");
                return;
            }
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
}
