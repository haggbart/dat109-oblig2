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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
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
    public String pickUp(@RequestParam Long id, HttpServletRequest request) {

        log.debug("id = {}", id);
        Reservation reservation = reservationService.getReservation(id);

        reservationService.addCreditCardNumber(request, reservation);

        // TODO: Get the one actually available
        Car car = carService.getAvailable(reservation);

        reservationService.rentOutCar(reservation, car);

        reservationService.save(reservation);

        return "redirect:/admin/reservations";
    }

    @PostMapping("/dropoff")
    public String dropOff(@RequestParam Long id, Model model, HttpServletRequest request) {
        log.debug("id = {}", id);

        Reservation reservation = reservationService.getReservation(id);

        Integer mileage = Integer.valueOf(request.getParameter("endMileage"));
        log.debug("new mileage = {}", mileage);

        Integer distance = carService.updateMileage(reservation, mileage);

        reservationService.carReturn(reservation);

        reservationService.save(reservation);

        var paymentDue = reservationService.makeReceipt(reservation);

        model.addAttribute("distance", distance);
        model.addAttribute("paymentDue", paymentDue);
        model.addAttribute("reservation", reservation);
        return "admin/receipt";
    }

}
