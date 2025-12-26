package com.example.cosmos_odyssey.service;

import com.example.cosmos_odyssey.repository.ReservationRepository;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
