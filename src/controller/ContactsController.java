package controller;

import DAO.ContactsDAO;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Contacts;

import java.net.URL;
import java.util.ResourceBundle;

public class ContactsController implements Initializable {
    public TableView <Contacts> contactsTable;
    public TableColumn <Contacts, Integer> contactId;
    public TableColumn <Contacts, String> contactName;
    public TableColumn <Contacts, String> contactEmail;

    //Use lambda to generate the report from the table
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactsTable.setItems(ContactsDAO.getAllContacts());
        contactId.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        contactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        contactEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
}
