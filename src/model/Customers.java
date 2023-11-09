package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

public class Customers {
    private int customerId;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDateTime createDate;
    private String division;
    private String country;

    public Customers (int customerId, String name, String address, String postalCode, String phone, LocalDateTime createDate, String division, String country){
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.division = division;
        this.country = country;
    }


    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getCreateDate() {return createDate;}

    public String getDivision() {
        return division;
    }
    public String getCountry(){
        return country;
    }
    /*ObservableList<Appointment> customerAppointments(){
        ObservableList<Appointment> getCustomerAppointments = FXCollections.observableArrayList();
        if(Customers.getCustomerId() == Appointment.getCustomerId()){

        }
        return getCustomerAppointments;
    }*/
    @Override
    public String toString(){
        return String.valueOf(customerId);
    }
}
