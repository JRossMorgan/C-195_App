package controller;

import DAO.AppointmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Appointment;
import model.Months;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
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
    public ComboBox <Months> monthBox;
    public DatePicker chooseWeek;

    public ObservableList<Appointment> monthlyAppointment = FXCollections.observableArrayList();
    public ObservableList<Appointment> weeklyAppointment = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Months month : Months.values()){
            monthBox.setValue(month);
        }
        appMonth.setItems(monthlyAppointment);
        idByMonth.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleByMonth.setCellValueFactory(new PropertyValueFactory<>("title"));
        descByMonth.setCellValueFactory(new PropertyValueFactory<>("description"));
        locByMonth.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactByMonth.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeByMonth.setCellValueFactory(new PropertyValueFactory<>("type"));
        startByMonth.setCellValueFactory(new PropertyValueFactory<>("start"));
        endByMonth.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerByMonth.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userByMonth.setCellValueFactory(new PropertyValueFactory<>("userId"));

        appWeek.setItems(weeklyAppointment);
        idByWeek.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleByWeek.setCellValueFactory(new PropertyValueFactory<>("title"));
        descByWeek.setCellValueFactory(new PropertyValueFactory<>("description"));
        locByWeek.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactByWeek.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeByWeek.setCellValueFactory(new PropertyValueFactory<>("type"));
        startByWeek.setCellValueFactory(new PropertyValueFactory<>("start"));
        endByWeek.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerByWeek.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userByWeek.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    public void onChooseMonth(ActionEvent actionEvent) {
        Months SP = monthBox.getSelectionModel().getSelectedItem();
        if(SP == null){
            return;
        }
        else{
            for(Appointment appointment : AppointmentDAO.getAppointments()){
                if(appointment.getStartTime().getMonth().equals(SP)){
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
                if(appointment.getStartTime().equals(SP)){

                }
            }
        }
    }
}
