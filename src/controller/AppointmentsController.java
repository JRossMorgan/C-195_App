package controller;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Months;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    public Tab byMonth;
    public TableView <Appointment> appMonth;
    public TableColumn <Appointment, Integer> idByMonth;
    public TableColumn <Appointment, String> titleByMonth;
    public TableColumn <Appointment, String> descByMonth;
    public TableColumn <Appointment, String> locByMonth;
    public TableColumn <Appointment, String> contactByMonth;
    public TableColumn <Appointment, String> typeByMonth;
    public TableColumn <Appointment, Timestamp> startByMonth;
    public TableColumn <Appointment, Timestamp> endByMonth;
    public TableColumn <Appointment, Integer> customerByMonth;
    public TableColumn <Appointment, Integer> userByMonth;
    public Tab byWeek;
    public TableView <Appointment> appWeek;
    public TableColumn <Appointment, Integer> idByWeek;
    public TableColumn <Appointment, String> titleByWeek;
    public TableColumn <Appointment, String> descByWeek;
    public TableColumn <Appointment, String> locByWeek;
    public TableColumn <Appointment, String> contactByWeek;
    public TableColumn <Appointment, String> typeByWeek;
    public TableColumn <Appointment, Timestamp> startByWeek;
    public TableColumn <Appointment, Timestamp> endByWeek;
    public TableColumn <Appointment, Integer> customerByWeek;
    public TableColumn <Appointment, Integer> userByWeek;
    public ComboBox <Month> monthBox;
    public DatePicker chooseWeek;

    public ObservableList<Appointment> monthlyAppointment = FXCollections.observableArrayList();
    public ObservableList<Appointment> weeklyAppointment = FXCollections.observableArrayList();
    public ObservableList <Appointment> testAppointment = FXCollections.observableArrayList();
    public ObservableList <Month> monthList = FXCollections.observableArrayList();
    public Button addMonth;
    public Button updateMonth;
    public Button deleteMonth;
    public Button addWeek;
    public Button updateWeek;
    public Button deleteWeek;
    public DialogPane appDialog;
    public TableView <Appointment> testTable;
    public TableColumn <Appointment, Integer> testId;
    public TableColumn <Appointment, String> testTitle;
    public TableColumn <Appointment, String> testDescription;
    public TableColumn <Appointment, String>testLocation;
    public TableColumn <Appointment, String> testContact;
    public TableColumn <Appointment, String> testType;
    public TableColumn <Appointment, LocalDateTime> testStart;
    public TableColumn <Appointment, LocalDateTime> testEnd;
    public TableColumn <Appointment, Integer> testCustomer;
    public TableColumn <Appointment, Integer> testUser;
    public Button appHome;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Month month : Month.values()){
            monthList.add(month);
            monthBox.setItems(monthList);
        }
        appMonth.setItems(monthlyAppointment);
        idByMonth.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleByMonth.setCellValueFactory(new PropertyValueFactory<>("title"));
        descByMonth.setCellValueFactory(new PropertyValueFactory<>("description"));
        locByMonth.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactByMonth.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeByMonth.setCellValueFactory(new PropertyValueFactory<>("type"));
        startByMonth.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endByMonth.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerByMonth.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userByMonth.setCellValueFactory(new PropertyValueFactory<>("userId"));

        appWeek.setItems(weeklyAppointment);
        idByWeek.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleByWeek.setCellValueFactory(new PropertyValueFactory<>("title"));
        descByWeek.setCellValueFactory(new PropertyValueFactory<>("description"));
        locByWeek.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactByWeek.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeByWeek.setCellValueFactory(new PropertyValueFactory<>("type"));
        startByWeek.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endByWeek.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerByWeek.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userByWeek.setCellValueFactory(new PropertyValueFactory<>("userId"));

        for(Appointment appointment : AppointmentDAO.getAppointments()){
            testAppointment.add(appointment);
        }

        testTable.setItems(testAppointment);
        testId.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        testTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        testDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        testLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        testContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        testType.setCellValueFactory(new PropertyValueFactory<>("type"));
        testStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        testEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        testCustomer.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        testUser.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    public void onChooseMonth(ActionEvent actionEvent) {
        Month SP = monthBox.getSelectionModel().getSelectedItem();
        if(SP == null){
            return;
        }
        else{
            for(Appointment appointment : AppointmentDAO.getAppointments()){
                if(appointment.getStartTime().getMonth() ==SP){
                    monthlyAppointment.add(appointment);
                }
            }
        }
    }

    public void onChooseWeek(ActionEvent actionEvent) {
        LocalDate SP = chooseWeek.getValue();
        if(SP == null){
            return;
        }
        else{
            for(Appointment appointment : AppointmentDAO.getAppointments()){
                int startWeek = SP.minusDays(3).getDayOfMonth();
                int endWeek = SP.plusDays(3).getDayOfMonth();
                if(appointment.getStartTime().getDayOfMonth() >= startWeek && appointment.getStartTime().getDayOfMonth() <= endWeek){
                    weeklyAppointment.add(appointment);
                }
            }
        }
    }

    public void onAddMonth(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onUpdateMonth(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onDeleteMonth(ActionEvent actionEvent) {
        Appointment SP = (Appointment) appMonth.getSelectionModel().getSelectedItem();
        if(SP == null){
            return;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Appointment");
            alert.setContentText("Are you sure you want to delete appointment?");
            Optional<ButtonType> pressed = alert.showAndWait();
            if(pressed.isPresent() && pressed.get() == ButtonType.OK){
                AppointmentDAO.deleteAppointment(SP.getAppointmentId());
                appDialog.setContentText("Appointment " + SP.getAppointmentId() + " " + SP.getType() + " has been canceled.");
                return;
            }
        }
    }

    public void onAddWeek(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onUpdateWeek(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/UpdateAppointment.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onDeleteWeek(ActionEvent actionEvent) {
        Appointment SP = (Appointment) appWeek.getSelectionModel().getSelectedItem();
        if(SP == null){
            return;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Appointment");
            alert.setContentText("Are you sure you want to delete appointment?");
            Optional<ButtonType> pressed = alert.showAndWait();
            if(pressed.isPresent() && pressed.get() == ButtonType.OK){
                AppointmentDAO.deleteAppointment(SP.getAppointmentId());
                appDialog.setContentText("Appointment " + SP.getAppointmentId() + " " + SP.getType() + " has been canceled.");
                return;
            }
        }
    }

    public void onHome(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Page.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
