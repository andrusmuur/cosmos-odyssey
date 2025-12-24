package com.example.cosmos_odyssey.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Set;

@Table("PRICE_LISTS")
public class PriceList {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSS][.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]'Z'", timezone = "UTC")
    private Instant validUntil;

    @MappedCollection(idColumn = "PRICE_LIST_ID")
    private Set<Route> legs;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public Set<Route> getLegs() {
        return legs;
    }

    public void setLegs(Set<Route> legs) {
        this.legs = legs;
    }
}
