package com.circle.usermanagementservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class User implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int userId;

    @Column( name = "FIRST_NAME" )
    private String firstName;

    @Column ( name = "LAST_NAME")
    private String lastName;

    @Column( name = "DATE_OF_BIRTH" )
    private Date dob;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private Address address;

    @Column( name = "MOBILE_NO_1" )
    private String mobile1;

    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    @JoinTable( joinColumns = { @JoinColumn( name = "userId") }, inverseJoinColumns = { @JoinColumn( name = "travelId") } )
    private List< TravelDetails > travelHistory;

    @Column( name = "BLOOD_GROUP" )
    private String bloodGroup;

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

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public List<TravelDetails> getTravelHistory() {
        return travelHistory;
    }

    public void setTravelHistory(List<TravelDetails> travelHistory) {
        this.travelHistory = travelHistory;
    }
}
