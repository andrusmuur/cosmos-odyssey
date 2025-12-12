package com.example.cosmos_odyssey.model;

public class Route {
    private String id;
    private RouteInfo routeInfo;
    private Provider[] providers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }

    public Provider[] getProviders() {
        return providers;
    }

    public void setProviders(Provider[] providers) {
        this.providers = providers;
    }
}
