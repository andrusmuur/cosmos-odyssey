package com.example.cosmos_odyssey.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table("RESERVATIONS")
public class Reservation {

    @JsonIgnore
    @Id
    private int id;

    private String firstName;
    private String lastName;

    @MappedCollection(idColumn = "RESERVATION_ID")
    private Set<ReservationRoute> routes;

    private float totalPrice;
    private int totalTravelTimeInSeconds;

    @MappedCollection(idColumn = "RESERVATION_ID")
    private Set<ReservationCompany> companyNames;

    @JsonProperty("travelPath")
    private void unpackNested(TravelPath travelPath) {
        routes = new HashSet<>();
        for (RouteInfo route: travelPath.getPath()) {
            routes.add(new ReservationRoute(route.getRouteId()));
        }

        Set<String> tempCompanyNames = new HashSet<>();
        List<Provider> pathProviders = travelPath.getPathProviders();
        for (Provider provider: pathProviders) {
            tempCompanyNames.add(provider.getCompanyName());
        }

        companyNames = new HashSet<>();
        for (String companyName: tempCompanyNames) {
            companyNames.add(new ReservationCompany(companyName));
        }

        totalPrice = travelPath.getTotalPrice();

        if (!pathProviders.isEmpty()) {
            Instant firstFlightStart = pathProviders.getFirst().getFlightStart();
            Instant lastFlightEnd = pathProviders.getLast().getFlightEnd();
            totalTravelTimeInSeconds = Math.toIntExact(Duration.between(firstFlightStart, lastFlightEnd).toSeconds());
        } else {
            totalTravelTimeInSeconds = 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalTravelTimeInSeconds() {
        return totalTravelTimeInSeconds;
    }

    public void setTotalTravelTimeInSeconds(int totalTravelTimeInSeconds) {
        this.totalTravelTimeInSeconds = totalTravelTimeInSeconds;
    }

    public Set<ReservationRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<ReservationRoute> routes) {
        this.routes = routes;
    }

    public Set<ReservationCompany> getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(Set<ReservationCompany> companyNames) {
        this.companyNames = companyNames;
    }
}