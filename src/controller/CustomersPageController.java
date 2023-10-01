package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;
import DAO.CustomersDAO;

import java.io.IOException;
import java.net.URL;
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
    }

    public void onMod(ActionEvent actionEvent) {
    }

    public void onDelete(ActionEvent actionEvent) {
    }

    public void onAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
