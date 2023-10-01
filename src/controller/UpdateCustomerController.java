package controller;

import DAO.CountryDAO;
import DAO.DivisionsDAO;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import model.Country;
import model.Customers;
import model.Divisions;

public class UpdateCustomerController {
    public TextField updateID;
    public TextField updateName;
    public TextField updateAddress;
    public TextField updatePostal;
    public TextField updatePhone;
    public ComboBox <Country> updateCountry;
    public ComboBox <Divisions> updateDivision;
    public Button updateSave;
    public Button updateCancel;
    public DialogPane updateDialog;

    public void onSelect(ActionEvent actionEvent) {
    }

    public void onUpdate(ActionEvent actionEvent) {
    }

    public void onUpdateCancel(ActionEvent actionEvent) {
    }
    public void updateCustomer(Customers customerToUpdate){
        updateID.setText(String.valueOf(customerToUpdate.getCustomerId()));
        updateName.setText(customerToUpdate.getName());
        updateAddress.setText(customerToUpdate.getAddress());
        updatePostal.setText(customerToUpdate.getPostalCode());
        updatePhone.setText(customerToUpdate.getPhone());
        for(Country customerCountry : CountryDAO.getCountries()) {
            if(customerCountry.name.equalsIgnoreCase(customerToUpdate.getCountry())){
                updateCountry.setValue(customerCountry);
            }
        }
        for(Divisions customerDivision : DivisionsDAO.getDivisions()){
            if(customerDivision.name.equalsIgnoreCase(customerToUpdate.getDivision())){
                updateDivision.setValue(customerDivision);
            }
        }
    }
}
