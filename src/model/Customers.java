package model;

/**
 * Customers class Customers.java
 */
/**
 *
 * @author Jedediah R Morgan
 */


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

    /**
     @return returns the customer Id */
    public int getCustomerId() {
        return customerId;
    }

    /**
     @return returns the customer name */
    public String getName() {
        return name;
    }

    /**
     @return returns the customer address */
    public String getAddress() {
        return address;
    }

    /**
     @return returns the customer postal code */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     @return returns the customer phone */
    public String getPhone() {
        return phone;
    }

    /**
     @return returns the create date */
    public LocalDateTime getCreateDate() {return createDate;}

    /**
     @return returns the customer division */
    public String getDivision() {
        return division;
    }

    /**
     @return returns the customer country */
    public String getCountry(){
        return country;
    }

    @Override
    /**
     @return overrides the toString method and returns the customer Id as a string */
    public String toString(){
        return String.valueOf(customerId);
    }
}
