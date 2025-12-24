package com.example.cosmos_odyssey.web;

import com.example.cosmos_odyssey.model.TravelPath;
import com.example.cosmos_odyssey.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class CosmosOdysseyController {

    private final RouteService routeService;

    public CosmosOdysseyController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public List<TravelPath> getValidPaths(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        if (origin.length() > 1 && destination.length() > 1) {
            origin = origin.substring(0, 1).toUpperCase() + origin.substring(1).toLowerCase();
            destination = destination.substring(0, 1).toUpperCase() + destination.substring(1).toLowerCase();
        }
        return routeService.getAllPaths(origin, destination);
    }
}
