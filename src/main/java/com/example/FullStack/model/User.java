package com.example.FullStack.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_info")
public class User {

    @Id
    private String email;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name="date")
    private Date date;

    public User(String email,String firstName, String lastName, String phoneNo, String address, String dateOfBirth, Date date) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.date = date;
    }

    public User() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth(){return dateOfBirth;}
    public void setDateOfBirth(String dateOfBirth){this.dateOfBirth= String.valueOf(dateOfBirth);}



    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}



    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
