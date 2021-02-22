package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.dto.ReservationDto;
import no.hvl.dat109.bilutleie.model.*;
import no.hvl.dat109.bilutleie.repository.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final ModelMapper mapper;

    public ReservationServiceImpl(ReservationRepository reservationRepository, CustomerService customerService, ModelMapper mapper) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation add(CustomerForDetailsDto customerDto, ReservationDto reservationDto) {

        Customer customer = customerService.add(customerDto);

        var reservation = new Reservation();
        mapper.map(reservationDto, reservation);
        customer.addReservation(reservation);
        reservation.setCustomer(customer);

        return save(reservation);
    }

    @Override
    public void rentOutCar(Reservation reservation, Car car) {
        reservation.setCar(car);
        reservation.setStartMileage(car.getMileage());
        reservation.setStatus(ReservationStatus.FETCHED);
        save(reservation);
    }

    @Override
    public void addCreditCardNumber(HttpServletRequest request, Reservation reservation) {
        String ccnReqAttribute = request.getParameter("ccn");
        Long creditCard = Long.valueOf(ccnReqAttribute);
        reservation.setCcn(creditCard);
        save(reservation);
    }

    @Override
    public void carReturn(Reservation reservation) {
        reservation.setEndMilage(reservation.getCar().getMileage());
        reservation.setStatus(ReservationStatus.RETURNED);
        save(reservation);
    }

    @Override
    public double makeReceipt(Reservation reservation) {
        var days = Duration.between(reservation.getStartDate(), reservation.getEndDate()).getSeconds() / (3600*24.0);

        var price = CarCategory.costPerDay(reservation.getCarCategory());

        return days * price;
    }

    @Override
    public long getCcn(Reservation reservation) {
        return reservation.getCcn();
    }
}
