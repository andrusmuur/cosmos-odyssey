package com.example.cosmos_odyssey.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Map;

public class Provider {
    private String id;
    private String companyName;
    private float price;

    @JsonProperty("company")
    private void unpackNested(Map<String, String> company) {
        this.companyName = company.get("name");
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS]'Z'", timezone = "UTC")
    private Instant flightStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS]'Z'", timezone = "UTC")
    private Instant flightEnd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Instant getFlightStart() {
        return flightStart;
    }

    public void setFlightStart(Instant flightStart) {
        this.flightStart = flightStart;
    }

    public Instant getFlightEnd() {
        return flightEnd;
    }

    public void setFlightEnd(Instant flightEnd) {
        this.flightEnd = flightEnd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
