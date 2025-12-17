package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.Route;
import com.example.cosmos_odyssey.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
