package com.example.cosmos_odyssey.repository;

import com.example.cosmos_odyssey.model.PriceList;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;

public interface PriceListRepository extends CrudRepository<PriceList, Integer> {

    @Query("SELECT valid_until FROM price_lists " +
            "ORDER BY valid_until DESC " +
            "LIMIT 1;")
    Instant getLatestPriceListExpiryDate();

    @Query("SELECT id FROM price_lists " +
            "ORDER BY valid_until DESC " +
            "LIMIT 1;")
    int getLatestPriceListId();

}
