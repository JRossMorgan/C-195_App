package controller;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Appointment;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public Button customers;
    public Button appointments;

    public ObservableList <Appointment> userAppointments = FXCollections.observableArrayList();

    public void setUser (Users userToSet){
        int loggedInUser = userToSet.getUserId();

        for(Appointment appointment : AppointmentDAO.getAppointments()){
            if(appointment.getUserId() == loggedInUser){
                userAppointments.add(appointment);
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
        LocalDateTime logInTime = LocalDateTime.now();
        ZonedDateTime zonedLogIn = ZonedDateTime.of(logInTime.toLocalDate(), logInTime.toLocalTime(), ZoneId.of("GMT"));

        LocalDateTime converted = zonedLogIn.toLocalDateTime();



    }

    public void onApp(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
