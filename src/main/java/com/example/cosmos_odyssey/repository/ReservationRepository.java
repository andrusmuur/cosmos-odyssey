package com.example.cosmos_odyssey.repository;

import com.example.cosmos_odyssey.model.Reservation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

    @Query("SELECT DISTINCT reservations.id FROM reservations " +
            "INNER JOIN reservation_routes ON reservation_id = reservations.id " +
            "INNER JOIN routes ON route_id = routes.id " +
            "WHERE price_list_id = :priceListId;")
    List<Integer> getReservationIdsOfPriceList(int priceListId);
}
