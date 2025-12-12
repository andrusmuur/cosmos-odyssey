package com.example.cosmos_odyssey.model;

public class RouteInfo {
    private String id;
    private Planet from;
    private Planet to;
    private long distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Planet getFrom() {
        return from;
    }

    public void setFrom(Planet from) {
        this.from = from;
    }

    public Planet getTo() {
        return to;
    }

    public void setTo(Planet to) {
        this.to = to;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
