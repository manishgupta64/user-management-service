package com.circle.usermanagementservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class User implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int userId;

    private String firstName;

    private String lastName;

    private Date dob;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private Address address;

    private String mobile1;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }
}
