package com.example.cosmos_odyssey.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Route {
    private String id;
    private Provider[] providers;
    private String from;
    private String to;
    private long distance;

    @JsonProperty("routeInfo")
    private void unpackNested(Map<String, Object> routeInfo) {
        Map<String, String> from = (Map<String, String>) routeInfo.get("from");
        Map<String, String> to = (Map<String, String>) routeInfo.get("to");
        this.distance = Long.parseLong(routeInfo.get("distance").toString());
        this.from = from.get("name");
        this.to = to.get("name");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Provider[] getProviders() {
        return providers;
    }

    public void setProviders(Provider[] providers) {
        this.providers = providers;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
