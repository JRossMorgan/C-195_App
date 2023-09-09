package controller;

import DBConnection.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogIn implements Initializable {
    public TextField userNameTF;
    public TextField passwordTF;
    public Button logIn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onLogIn(ActionEvent actionEvent) throws IOException {
        String userName = userNameTF.getText();
        if(userName.isBlank()){
            return;
        }
        String password = passwordTF.getText();
        if(password.isBlank()){
            return;
        }

        for(Users user : Users.getAllUsers()){
            if(userName.equals(user.getUserName()) && password.equals(user.getPassword())){
                Parent root = FXMLLoader.load(getClass().getResource("/view/Main_Page.fxml"));
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        }


    }


}