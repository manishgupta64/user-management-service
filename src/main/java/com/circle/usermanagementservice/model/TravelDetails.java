package com.circle.usermanagementservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TravelDetails implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int travelId;

    private String src;

    private String destination;

    private Date journeyDate;

    public int getTravelId() {
        return travelId;
    }

    public void setTravelId(int travelId) {
        this.travelId = travelId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getJourneyDate() {
        return journeyDate;
    }

    public void setJourneyDate(Date journeyDate) {
        this.journeyDate = journeyDate;
    }

}
