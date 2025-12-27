package com.example.cosmos_odyssey.web;

import com.example.cosmos_odyssey.model.Reservation;
import com.example.cosmos_odyssey.model.TravelPath;
import com.example.cosmos_odyssey.service.ReservationService;
import com.example.cosmos_odyssey.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CosmosOdysseyController {

    private final RouteService routeService;
    private final ReservationService reservationService;

    public CosmosOdysseyController(RouteService routeService, ReservationService reservationService) {
        this.routeService = routeService;
        this.reservationService = reservationService;
    }

    @GetMapping("/routes")
    public List<TravelPath> getValidPaths(@RequestParam("origin") String origin, @RequestParam("destination") String destination, @RequestParam String sortBy, @RequestParam String companyName) {
        if (origin.length() > 1 && destination.length() > 1) {
            origin = origin.substring(0, 1).toUpperCase() + origin.substring(1).toLowerCase();
            destination = destination.substring(0, 1).toUpperCase() + destination.substring(1).toLowerCase();
        }
        return routeService.getValidPaths(origin, destination, sortBy, companyName);
    }

    @PostMapping("/reservation")
    public boolean postReservation(@RequestBody Reservation reservation) {
        if (routeService.routesAreValid(reservation.getRoutes())) {
            reservationService.saveReservation(reservation);
            return true;
        } else {
            return false;
        }
    }
}
