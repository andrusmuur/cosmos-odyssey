package com.example.cosmos_odyssey.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class PriceList {

    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS]'Z'", timezone = "UTC")
    private Instant validUntil;

    private Route[] legs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public Route[] getLegs() {
        return legs;
    }

    public void setLegs(Route[] legs) {
        this.legs = legs;
    }
}
