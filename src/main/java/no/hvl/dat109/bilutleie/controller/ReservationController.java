package no.hvl.dat109.bilutleie.controller;


import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.Car;
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
        Long ccn = 123412341234L;
        reservation.setCcn(ccn);

        Car car = carService.getAvailable(reservation.getPickup(), reservation.getCarCategory());
        reservation.setCar(car);
        reservation.setStartMileage(car.getMileage());

        reservation.setStatus(ReservationStatus.FETCHED);

        reservationService.save(reservation);

        return "redirect:/admin/reservations";
    }
}
