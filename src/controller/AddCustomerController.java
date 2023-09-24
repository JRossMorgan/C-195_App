package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    public TextField addName;
    public TextField addAddress;
    public TextField addPostal;
    public TextField addPhone;
    public ComboBox addCountry;
    public ComboBox addDivision;
    public Button addSave;
    public Button addCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onAdd(ActionEvent actionEvent) {
    }

    public void onAddCancel(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Page.fxml"));
        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
