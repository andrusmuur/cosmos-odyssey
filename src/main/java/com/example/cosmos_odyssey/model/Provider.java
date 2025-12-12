package com.example.cosmos_odyssey.model;

public class Provider {
    private String id;
    private Company company;
    private float price;
    private String flightStart;
    private String flightEnd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getFlightStart() {
        return flightStart;
    }

    public void setFlightStart(String flightStart) {
        this.flightStart = flightStart;
    }

    public String getFlightEnd() {
        return flightEnd;
    }

    public void setFlightEnd(String flightEnd) {
        this.flightEnd = flightEnd;
    }
}
