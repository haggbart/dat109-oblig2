package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.dto.ReservationDto;
import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.Reservation;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReservationService {
    Reservation save(Reservation reservation);

    List<Reservation> getReservations();

    Reservation getReservation(Long id);

    Reservation add(CustomerForDetailsDto customerDto, ReservationDto reservationDto);

    void rentOutCar(Reservation reservation, Car car);

    void addCreditCardNumber(HttpServletRequest request, Reservation reservation);

    void carReturn(Reservation reservation);

    double makeReceipt(Reservation reservation);

    long getCcn(Reservation reservation);
}
