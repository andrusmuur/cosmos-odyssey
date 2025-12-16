package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.PriceList;
import com.example.cosmos_odyssey.repository.PriceListRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PriceListService {
    private final PriceListRepository priceListRepository;

    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public Iterable<PriceList> get() {
        return  priceListRepository.findAll();
    }

    public void save(PriceList priceList) {
        priceListRepository.save(priceList);
    }

    public boolean latestPriceListIsExpired() {
        Instant latestPriceListExpiryDate = priceListRepository.getLatestPriceListExpiryDate();
        return latestPriceListExpiryDate == null || Instant.now().isAfter(latestPriceListExpiryDate);
    }
}
