package com.example.cosmos_odyssey.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

@Table("PROVIDERS")
public class Provider {

    @JsonIgnore
    @Id
    private int id;

    private String companyName;

    private float price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS]'Z'", timezone = "UTC")
    private Instant flightStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS]'Z'", timezone = "UTC")
    private Instant flightEnd;


    @JsonProperty("company")
    private void unpackNested(Map<String, String> company) {
        this.companyName = company.get("name");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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
