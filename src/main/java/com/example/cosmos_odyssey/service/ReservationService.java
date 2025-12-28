package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.model.Reservation;
import com.example.cosmos_odyssey.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void deleteByPriceList(int priceListId) {
        List<Integer> reservationIds = reservationRepository.getReservationIdsOfPriceList(priceListId);
        for (int id: reservationIds) {
            reservationRepository.deleteById(id);
        }
    }
}
