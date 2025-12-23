package com.example.cosmos_odyssey.model;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class TravelPath {
    private final List<RouteInfo> path;
    private final List<Provider> pathProviders;
    private final float totalPrice;
    private final long totalDistance;
    private final long totalTravelTimeInDays;

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

    public List<Provider> getPathProviders() {
        return pathProviders;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public long getTotalDistance() {
        return totalDistance;
    }

    public long getTotalTravelTimeInDays() {
        return totalTravelTimeInDays;
    }
}
