package com.example.cosmos_odyssey.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("RESERVATIONS")
public class Reservation {

    @JsonIgnore
    @Id
    private int id;

    private String firstName;
    private String lastName;
    private TravelPath travelPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TravelPath getTravelPath() {
        return travelPath;
    }

    public void setTravelPath(TravelPath travelPath) {
        this.travelPath = travelPath;
    }
}