package model;

/**
 * Appointment class Appointment.java
 */
/**
 *
 * @author Jedediah R Morgan
 */

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String contact;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;
    private int contactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, String contact, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId){
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.contact = contact;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     @return returns the appointment Id */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     @return returns the title */
    public String getTitle() {
        return title;
    }

    /**
     @return returns the description */
    public String getDescription() {
        return description;
    }

    /**
     @return returns the location */
    public String getLocation() {
        return location;
    }

    /**
     @return returns the type */
    public String getType() {
        return type;
    }

    /**
     @return returns the contact */
    public String getContact() {
        return contact;
    }

    /**
     @return returns the start time */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     @return returns the end time */
    public LocalDateTime getEndTime() {return endTime;}

    /**
     @return returns the customer Id */
    public int getCustomerId() {return customerId; }

    /**
     @return returns the user Id */
    public int getUserId() {
        return userId;
    }
}
