package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.ReservationRoute;
import com.example.cosmos_odyssey.model.RouteInfo;
import com.example.cosmos_odyssey.model.TravelPath;
import com.example.cosmos_odyssey.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final PriceListService priceListService;
    private final ProviderService providerService;

    public RouteService(RouteRepository routeRepository, PriceListService priceListService, ProviderService providerService) {
        this.routeRepository = routeRepository;
        this.priceListService = priceListService;
        this.providerService = providerService;
    }

    public boolean routeIsExpired(int routeId) {
        Instant priceListExpiryDateOfRoute = routeRepository.getPriceListExpiryDateOfRoute(routeId);
        return priceListExpiryDateOfRoute == null || Instant.now().isAfter(priceListExpiryDateOfRoute);
    }

    public boolean routesAreValid(Set<ReservationRoute> routes) {
        for (ReservationRoute route: routes) {
            if(routeIsExpired(route.getRouteId())) {
                return false;
            }
        }
        return true;
    }

    public List<RouteInfo> getValidRouteInfo() {
        priceListService.ensurePriceListIsValid();
        return  routeRepository.getRouteInfo(priceListService.getLatestPriceListId());
    }

    public List<TravelPath> getValidPaths(String origin, String destination, String sortBy, String companyName) {
        List<RouteInfo> validRoutes = getValidRouteInfo();
        List<List<RouteInfo>> paths = new ArrayList<>();
        findAllPaths(origin, destination, validRoutes, paths, new ArrayList<>());

        List<TravelPath> travelPaths = new ArrayList<>();
        for (List<RouteInfo> path: paths) {
            List<TravelPath> newPaths = providerService.getAllProviders(path, companyName);
            travelPaths.addAll(newPaths);
        }

        switch (sortBy) {
            case "price":
                travelPaths.sort(Comparator.comparing(TravelPath::getTotalPrice));
                break;
            case "distance":
                travelPaths.sort(Comparator.comparing(TravelPath::getTotalDistance));
                break;
            case "travelTime":
                travelPaths.sort(Comparator.comparing(TravelPath::getTotalTravelTimeInDays));
                break;
        }

        return travelPaths;
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
