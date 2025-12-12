package com.example.cosmos_odyssey.model;

public class PriceList {

    private String id;
    private String validUntil;
    private Route[] legs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public Route[] getLegs() {
        return legs;
    }

    public void setLegs(Route[] legs) {
        this.legs = legs;
    }
}
