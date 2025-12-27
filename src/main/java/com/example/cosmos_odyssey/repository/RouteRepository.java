package com.example.cosmos_odyssey.repository;

import com.example.cosmos_odyssey.model.Route;
import com.example.cosmos_odyssey.model.RouteInfo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Integer> {
    @Query("SELECT id, from_planet, to_planet, distance FROM routes " +
            "WHERE price_list_id = :priceListId;")
    List<RouteInfo> getRouteInfo(int priceListId);

    @Query("SELECT valid_until FROM routes " +
            "INNER JOIN price_lists ON price_list_id = price_lists.id " +
            "WHERE routes.id = :routeId;")
    Instant getPriceListExpiryDateOfRoute(int routeId);
}
