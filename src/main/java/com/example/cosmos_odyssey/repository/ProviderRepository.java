package com.example.cosmos_odyssey.repository;

import com.example.cosmos_odyssey.model.Provider;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface ProviderRepository extends CrudRepository<Provider, Integer> {

    @Query("SELECT * FROM providers " +
            "WHERE route_id = :routeId;")
    List<Provider> getRouteProviders(int routeId);

    @Query("SELECT * FROM providers " +
            "WHERE route_id = :routeId " +
            "AND flight_start > :date;")
    List<Provider> getRouteProvidersAfterDate(int routeId, Instant date);

    @Query("SELECT * FROM providers " +
            "WHERE route_id = :routeId " +
            "AND company_name = :companyName;")
    List<Provider> getRouteProviders(int routeId, String companyName);

    @Query("SELECT * FROM providers " +
            "WHERE route_id = :routeId " +
            "AND flight_start > :date " +
            "AND company_name = :companyName;")
    List<Provider> getRouteProvidersAfterDate(int routeId, Instant date, String companyName);
}
