package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.dto.ReservationDto;
import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.Reservation;
import no.hvl.dat109.bilutleie.model.ReservationStatus;
import no.hvl.dat109.bilutleie.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper mapper;

    public ReservationService(ReservationRepository reservationRepository, ModelMapper mapper) {
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
    }

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        mapper.map(reservationDto, reservation);
        return reservation;
    }

    public void rentOutCar(Reservation reservation, Car car) {
        reservation.setCar(car);
        reservation.setStartMileage(car.getMileage());
        reservation.setStatus(ReservationStatus.FETCHED);
    }

    public void carReturn(Reservation reservation) {
        reservation.setEndMilage(reservation.getCar().getMileage());
        reservation.setStatus(ReservationStatus.RETURNED);
    }

    public double makeReceipt(Reservation reservation) {
        var days = Duration.between(reservation.getStartDate(), reservation.getEndDate()).getSeconds() / (3600*24.0);

        var price = CarCategory.costPerDay(reservation.getCarCategory());

        return days * price;
    }

    public long getCcn(Reservation reservation) {
        return reservation.getCcn();
    }
}
