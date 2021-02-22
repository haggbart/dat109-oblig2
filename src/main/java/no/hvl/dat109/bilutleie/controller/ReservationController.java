package no.hvl.dat109.bilutleie.controller;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.Reservation;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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
    public String dropOff(@RequestParam Long id, @RequestParam Integer endMileage, Model model) {
        log.debug("id = {}", id);

        Reservation reservation = reservationService.getReservation(id);

        log.debug("new mileage = {}", endMileage);

        Integer distance = carService.updateMileage(reservation, endMileage);

        reservationService.carReturn(reservation);

        reservationService.save(reservation);

        var paymentDue = reservationService.makeReceipt(reservation);

        model.addAttribute("distance", distance);
        model.addAttribute("paymentDue", paymentDue);
        model.addAttribute("reservation", reservation);
        return "admin/receipt";
    }
}
