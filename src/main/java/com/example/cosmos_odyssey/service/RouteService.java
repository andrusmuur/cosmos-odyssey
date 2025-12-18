package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.Route;
import com.example.cosmos_odyssey.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final PriceListService priceListService;

    public RouteService(RouteRepository routeRepository, PriceListService priceListService) {
        this.routeRepository = routeRepository;
        this.priceListService = priceListService;
    }

    public List<Route> getValidRoutes(String origin, String destination) {
        priceListService.ensurePriceListIsValid();
        return routeRepository.getValidRoutes(origin, destination, priceListService.getLatestPriceListId());
    }

    public List<Route> getValidRoutes() {
        priceListService.ensurePriceListIsValid();
        return routeRepository.getValidRoutes(priceListService.getLatestPriceListId());
    }

    public List<List<Route>> getAllPaths(String origin, String destination) {
        List<Route> validRoutes = getValidRoutes();
        List<List<Route>> paths = new ArrayList<>();
        findAllPaths(origin, destination, validRoutes, paths, new ArrayList<>());
        return paths;
    }

    private void findAllPaths(String origin, String destination, List<Route> validRoutes, List<List<Route>> paths, List<Route> path) {
        if (origin.equals(destination)) {
            paths.add(new ArrayList<>(path));
            return;
        }

        for (Route route: validRoutes) {
            if(route.getFromPlanet().equals(origin) && planetIsNotPassedThrough(route.getToPlanet(), path)) {
                path.add(route);
                findAllPaths(route.getToPlanet(),destination, validRoutes, paths, path);
                path.removeLast();
            }
        }
    }

    private boolean planetIsNotPassedThrough(String planet, List<Route> path) {
        for (Route route: path) {
            if(route.getFromPlanet().equals(planet) || route.getToPlanet().equals(planet)) {
                return false;
            }
        }
        return true;
    }

}
