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

    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "phone_no")
    private String phoneNo;
    @Column(name = "address")
    private String address;




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

    public Date getDateOfBirth(){return dateOfBirth;}
    public void setDateOfBirth(Date dateOfBirth){this.dateOfBirth=dateOfBirth;}



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
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
