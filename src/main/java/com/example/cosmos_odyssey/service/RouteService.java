package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.Route;
import com.example.cosmos_odyssey.model.RouteInfo;
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

    public List<RouteInfo> getValidRouteInfo() {
        priceListService.ensurePriceListIsValid();
        return  routeRepository.getRouteInfo(priceListService.getLatestPriceListId());
    }

    public List<List<RouteInfo>> getAllPaths(String origin, String destination) {
        List<RouteInfo> validRoutes = getValidRouteInfo();
        List<List<RouteInfo>> paths = new ArrayList<>();
        findAllPaths(origin, destination, validRoutes, paths, new ArrayList<>());
        return paths;
    }

    private void findAllPaths(String origin, String destination, List<RouteInfo> validRoutes, List<List<RouteInfo>> paths, List<RouteInfo> path) {
        if (origin.equals(destination)) {
            paths.add(new ArrayList<>(path));
            return;
        }

        for (RouteInfo routeInfo: validRoutes) {
            if(routeInfo.getFromPlanet().equals(origin) && planetIsNotPassedThrough(routeInfo.getToPlanet(), path)) {
                path.add(routeInfo);
                findAllPaths(routeInfo.getToPlanet(),destination, validRoutes, paths, path);
                path.removeLast();
            }
        }
    }

    private boolean planetIsNotPassedThrough(String planet, List<RouteInfo> path) {
        for (RouteInfo routeInfo: path) {
            if(routeInfo.getFromPlanet().equals(planet) || routeInfo.getToPlanet().equals(planet)) {
                return false;
            }
        }
        return true;
    }

}
