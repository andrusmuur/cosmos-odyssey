package com.example.cosmos_odyssey.web;

import com.example.cosmos_odyssey.model.Route;
import com.example.cosmos_odyssey.model.RouteInfo;
import com.example.cosmos_odyssey.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CosmosOdysseyController {

    private final RouteService routeService;

    public CosmosOdysseyController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public List<List<RouteInfo>> getValidPaths(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        return routeService.getAllPaths(origin, destination);
    }

}
