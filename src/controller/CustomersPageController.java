package controller;

import DAO.AppointmentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customers;
import DAO.CustomersDAO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomersPageController implements Initializable {
    public TableView <Customers> customersTable;
    public TableColumn <Customers, Integer> customerId;
    public TableColumn <Customers, String> customerName;
    public TableColumn <Customers, String> customerAddress;
    public TableColumn <Customers, String> postalCode;
    public TableColumn <Customers, String> custPhone;
    public TableColumn <Customers,String> custDivision;
    public TableColumn <Customers, String> custCountry;
    public Button modCustomer;
    public Button deleteCustomer;
    public Button addCustomer;
    public DialogPane custDialog;
    public Button home;
    public ComboBox <Month> reportMonth;
    public ComboBox <String> reportType;
    public Button generateReport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customersTable.setItems(CustomersDAO.getAllCustomers());

        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custDivision.setCellValueFactory(new PropertyValueFactory<>("division"));
        custCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        for(Appointment appointment : AppointmentDAO.getAppointments()){
            reportMonth.setValue(appointment.getStartTime().getMonth());
        }

        for(Appointment type : AppointmentDAO.getAppointments()){
            reportType.setValue(type.getType());
        }
    }

    public void onMod(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/UpdateCustomer.fxml"));
        loader.load();

        UpdateCustomerController UpdatingCustomer = loader.getController();
        UpdatingCustomer.updateCustomer((Customers)customersTable.getSelectionModel().getSelectedItem());
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onDelete(ActionEvent actionEvent) {
        Customers SP = (Customers) customersTable.getSelectionModel().getSelectedItem();
        if(SP == null){
            return;
        }
        else{
            for(Appointment appointment : AppointmentDAO.getAppointments()){
                if(appointment.getCustomerId() == SP.getCustomerId()){
                    custDialog.setContentText("You Cannot Delete a Customer With Scheduled Appointments");
                    return;
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("DELETE");
                    alert.setContentText("Are you sure you want to delete this customer?");
                    Optional<ButtonType> pressed = alert.showAndWait();
                    if(pressed.isPresent() && pressed.get() == ButtonType.OK){
                        CustomersDAO.deleteCustomer(SP.getCustomerId());
                        customersTable.refresh();
                        custDialog.setContentText("Customer Deleted");
                        return;
                    }
                }
            }
        }
    }

    public void onAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onHome(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Page.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onGenerate(ActionEvent actionEvent) {
        Month getMonth = reportMonth.getSelectionModel().getSelectedItem();
        if(getMonth == null){
            custDialog.setContentText("Please choose a month");
        }
        String getType = reportType.getSelectionModel().getSelectedItem();
        if(getType.isBlank()){
            custDialog.setContentText("Please choose a type");
        }
        int numAppointments = 0;
        for(Appointment appointment : AppointmentDAO.getAppointments()){
            if(appointment.getStartTime().getMonth().equals(getMonth) && appointment.getType().equalsIgnoreCase(getType)){
                numAppointments = numAppointments++;
            }
            custDialog.setContentText("There are " + numAppointments + " " + getType + " appointments in " + getMonth);
        }
    }
}
