package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.model.Reservation;
import no.hvl.dat109.bilutleie.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
