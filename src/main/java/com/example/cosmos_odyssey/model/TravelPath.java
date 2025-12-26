package com.example.cosmos_odyssey.model;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class TravelPath {
    private List<RouteInfo> path;
    private List<Provider> pathProviders;
    private float totalPrice;
    private long totalDistance;
    private long totalTravelTimeInDays;

    public TravelPath(List<RouteInfo> path, List<Provider> pathProviders) {
        this.path = path;
        this.pathProviders = pathProviders;

        long tempTotalDistance = 0;
        for (RouteInfo route: path) {
            tempTotalDistance += route.getDistance();
        }
        totalDistance = tempTotalDistance;

        float tempTotalPrice = 0;
        for (Provider provider: pathProviders) {
            tempTotalPrice += provider.getPrice();
        }
        totalPrice = tempTotalPrice;

        if (!pathProviders.isEmpty()) {
            Instant firstFlightStart = pathProviders.getFirst().getFlightStart();
            Instant lastFlightEnd = pathProviders.getLast().getFlightEnd();
            totalTravelTimeInDays = Duration.between(firstFlightStart, lastFlightEnd).toDays();
        } else {
            totalTravelTimeInDays = 0;
        }
    }

    public List<RouteInfo> getPath() {
        return path;
    }

    public void setPath(List<RouteInfo> path) {
        this.path = path;
    }

    public List<Provider> getPathProviders() {
        return pathProviders;
    }

    public void setPathProviders(List<Provider> pathProviders) {
        this.pathProviders = pathProviders;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(long totalDistance) {
        this.totalDistance = totalDistance;
    }

    public long getTotalTravelTimeInDays() {
        return totalTravelTimeInDays;
    }

    public void setTotalTravelTimeInDays(long totalTravelTimeInDays) {
        this.totalTravelTimeInDays = totalTravelTimeInDays;
    }
}
