package com.example.mad_project.entity;

public class Contact {
    private int ID;
    private String firstName, lastName, contactNumber;

    //alt+insert
    public Contact(String firstName, String lastName, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public Contact(int ID, String firstName, String lastName, String contactNumber) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
