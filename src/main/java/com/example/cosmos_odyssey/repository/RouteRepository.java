package com.example.cosmos_odyssey.repository;

import com.example.cosmos_odyssey.model.Route;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteRepository extends CrudRepository<Route, Integer> {

    @Query("SELECT * FROM routes " +
            "WHERE from_planet = :origin " +
            "AND to_planet = :destination " +
            "AND price_list_id = :priceListId;")
    List<Route> getValidRoutes(String origin, String destination, int priceListId);

    @Query("SELECT * FROM routes " +
            "WHERE price_list_id = :priceListId;")
    List<Route> getValidRoutes(int priceListId);
}
