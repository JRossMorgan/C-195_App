package model;

/**
 * Contacts class Contacts.java
 */
/**
 *
 * @author Jedediah R Morgan
 */

public class Contacts {
    private int contactId;
    private String contactName;
    private String email;

    public Contacts(int contactId, String contactName, String email){
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     @return returns the contact Id */
    public int getContactId(){return contactId;}

    /**
     @return returns the contact name */
    public String getContactName() {
        return contactName;
    }

    /**
     @return returns the contact email */
    public String getEmail() {
        return email;
    }

    @Override

    /**
     @return overrides the toString method and returns the contact name */
    public String toString(){
        return contactName;
    }
}
