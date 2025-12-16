package com.example.cosmos_odyssey.web;

import com.example.cosmos_odyssey.model.PriceList;
import com.example.cosmos_odyssey.service.PriceListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class PriceListController {

    private final PriceListService priceListService;

    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping("/routes")
    public String getRoutes(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
        if (priceListService.latestPriceListIsExpired()) {
            RestClient restClient = RestClient.create();
            PriceList priceList = restClient.get()
                    .uri("https://cosmosodyssey.azurewebsites.net/api/v1.0/TravelPrices")
                    .retrieve()
                    .body(PriceList.class);
            priceListService.save(priceList);
        }

        return priceListService.get().toString();
    }


}
