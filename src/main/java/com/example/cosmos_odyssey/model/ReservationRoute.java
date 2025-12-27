package com.example.cosmos_odyssey.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("RESERVATION_ROUTES")
public class ReservationRoute {

    @Id
    private int id;

    private int routeId;

    public ReservationRoute(int routeId) {
        this.routeId = routeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }
}
