package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.PriceList;
import com.example.cosmos_odyssey.repository.PriceListRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;

@Service
public class PriceListService {
    private final PriceListRepository priceListRepository;

    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public boolean latestPriceListIsExpired() {
        Instant latestPriceListExpiryDate = priceListRepository.getLatestPriceListExpiryDate();
        return latestPriceListExpiryDate == null || Instant.now().isAfter(latestPriceListExpiryDate);
    }

    public void ensurePriceListIsValid() {
        if (latestPriceListIsExpired()) {
            RestClient restClient = RestClient.create();
            PriceList newPriceList = restClient.get()
                    .uri("https://cosmosodyssey.azurewebsites.net/api/v1.0/TravelPrices")
                    .retrieve()
                    .body(PriceList.class);
            priceListRepository.save(newPriceList);
        }
    }

    public int getLatestPriceListId() {
        return priceListRepository.getLatestPriceListId();
    }
}
