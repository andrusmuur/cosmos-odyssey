package com.example.cosmos_odyssey.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Map;

@Table("PROVIDERS")
public class Provider {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    private int id;

    private String companyName;

    private float price;

    private Instant flightStart;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    public Instant getFlightStart() {
        return flightStart;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]'Z'", timezone = "UTC")
    public void setFlightStart(Instant flightStart) {
        this.flightStart = flightStart;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    public Instant getFlightEnd() {
        return flightEnd;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]'Z'", timezone = "UTC")
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
