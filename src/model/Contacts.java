package model;

public class Contacts {
    private String contactName;
    private String email;

    public Contacts(String contactName, String email){
        this.contactName = contactName;
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }
}
