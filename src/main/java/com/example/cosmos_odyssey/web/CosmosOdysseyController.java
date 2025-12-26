package com.example.cosmos_odyssey.web;

import com.example.cosmos_odyssey.model.Reservation;
import com.example.cosmos_odyssey.model.TravelPath;
import com.example.cosmos_odyssey.service.PriceListService;
import com.example.cosmos_odyssey.service.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CosmosOdysseyController {

    private final RouteService routeService;
    private final PriceListService priceListService;

    public CosmosOdysseyController(RouteService routeService, PriceListService priceListService) {
        this.routeService = routeService;
        this.priceListService = priceListService;
    }

    @GetMapping("/routes")
    public List<TravelPath> getValidPaths(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        if (origin.length() > 1 && destination.length() > 1) {
            origin = origin.substring(0, 1).toUpperCase() + origin.substring(1).toLowerCase();
            destination = destination.substring(0, 1).toUpperCase() + destination.substring(1).toLowerCase();
        }
        return routeService.getAllPaths(origin, destination);
    }

    @PostMapping("/reservation")
    public void postReservation(@RequestBody Reservation reservation) {
        //if (priceListService.latestPriceListIsExpired())
        System.out.println(reservation.getFirstName());
        System.out.println(reservation.getLastName());
        System.out.println(reservation.getTravelPath());
    }
}
