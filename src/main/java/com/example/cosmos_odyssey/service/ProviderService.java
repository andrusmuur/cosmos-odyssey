package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.Provider;
import com.example.cosmos_odyssey.model.RouteInfo;
import com.example.cosmos_odyssey.model.TravelPath;
import com.example.cosmos_odyssey.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public List<Provider> getRouteProviders(int routeId) {
        return providerRepository.getRouteProviders(routeId);
    }

    public List<TravelPath> getAllProviders(List<RouteInfo> routes) {
        List<TravelPath> travelPaths = new ArrayList<>();
        List<Provider> pathProviders = new ArrayList<>();
        findAllProviders(routes, 0,pathProviders ,travelPaths);
        return travelPaths;
    }

    private void findAllProviders(List<RouteInfo> routes, int routeIndex, List<Provider> pathProviders, List<TravelPath> travelPaths) {
        if (routeIndex >= routes.size()) {
            TravelPath travelPath = new TravelPath(new ArrayList<>(routes), new ArrayList<>(pathProviders));
            travelPaths.add(travelPath);
            return;
        }

        List<Provider> currentRouteProviders;
        RouteInfo currentRoute = routes.get(routeIndex);
        if (routeIndex == 0) {
            currentRouteProviders = providerRepository.getRouteProviders(currentRoute.getRouteId());
        } else {
            Provider previousProvider = pathProviders.get(routeIndex - 1);
            currentRouteProviders = providerRepository.getRouteProvidersAfterDate(currentRoute.getRouteId(), previousProvider.getFlightEnd());
        }

        for (Provider provider: currentRouteProviders) {
            pathProviders.add(provider);
            findAllProviders(routes, routeIndex + 1, pathProviders, travelPaths);
            pathProviders.removeLast();
        }
    }
}
