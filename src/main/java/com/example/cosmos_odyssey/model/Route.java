package com.example.cosmos_odyssey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Map;
import java.util.Set;

@Table("ROUTES")
public class Route {

    @JsonIgnore
    @Id
    private int id;

    @MappedCollection(idColumn = "ROUTE_ID")
    private Set<Provider> providers;

    private String fromPlanet;

    private String toPlanet;

    private long distance;


    @JsonProperty("routeInfo")
    private void unpackNested(Map<String, Object> routeInfo) {
        Map<String, String> from = (Map<String, String>) routeInfo.get("from");
        Map<String, String> to = (Map<String, String>) routeInfo.get("to");
        this.distance = Long.parseLong(routeInfo.get("distance").toString());
        this.fromPlanet = from.get("name");
        this.toPlanet = to.get("name");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Provider> getProviders() {
        return providers;
    }

    public void setProviders(Set<Provider> providers) {
        this.providers = providers;
    }

    public String getFromPlanet() {
        return fromPlanet;
    }

    public void setFromPlanet(String fromPlanet) {
        this.fromPlanet = fromPlanet;
    }

    public String getToPlanet() {
        return toPlanet;
    }

    public void setToPlanet(String toPlanet) {
        this.toPlanet = toPlanet;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
