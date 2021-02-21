package no.hvl.dat109.bilutleie.controller;


import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.Reservation;
import no.hvl.dat109.bilutleie.model.ReservationStatus;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.Period;


@Slf4j
@RequestMapping("/admin/reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final CarService carService;

    public ReservationController(ReservationService reservationService, CarService carService) {
        this.reservationService = reservationService;
        this.carService = carService;
    }

    @GetMapping
    public String getReservations(Model model) {

        model.addAttribute("reservations", reservationService.getReservations());
        return "admin/reservations";
    }

    // reserved, fetched, returned
    @PostMapping("/pickup")
    public String pickUp(@RequestParam Long id) {
        log.debug("id = {}", id);

        Reservation reservation = reservationService.getReservation(id);

        // TODO credit card number from input
        Long ccn = 1234L;
        log.debug("ccn = {}", ccn);
        reservation.setCcn(ccn);

        // TODO: Get the one actually available
        Car car = carService.getAvailable(reservation);

        reservationService.rentOutCar(reservation, car);

        reservationService.save(reservation);

        return "redirect:/admin/reservations";
    }

    @PostMapping("/dropoff")
    public String dropOff(@RequestParam Long id, Model model) {
        log.debug("id = {}", id);

        Reservation reservation = reservationService.getReservation(id);

        // TODO admin må oppgi input for endMilage
        reservationService.carReturn(reservation);

        reservationService.save(reservation);

        var paymentDue = reservationService.makeReceipt(reservation);

        model.addAttribute("paymentDue", paymentDue);
        model.addAttribute("reservation", reservation);
        return "admin/receipt";
    }

}
