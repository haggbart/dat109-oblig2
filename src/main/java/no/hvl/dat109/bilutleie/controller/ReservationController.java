package no.hvl.dat109.bilutleie.controller;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.dto.ReservationDto;
import no.hvl.dat109.bilutleie.dto.ReservationForLocationTimeDto;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Slf4j
@RequestMapping("/reservation")
@Controller
public class ReservationController {

    private final CarService carService;
    private final RentalOfficeService officeService;

    public ReservationController(CarService carService, RentalOfficeService officeService) {
        this.carService = carService;
        this.officeService = officeService;
    }


    @GetMapping("/offerselect")
    public String locationTimeForm(HttpSession session, Model model) {

        var reservation = (ReservationDto) session.getAttribute("reservation");

        model.addAttribute("city", reservation.getPickup().getAddress().getCity());
        model.addAttribute("cars", carService.getCarsByOffice(reservation.getPickup()));

        return "offerselect";
    }

    @PostMapping("/locationtime")
    public String locationTimeSubmit(@Valid @ModelAttribute("locationTime") ReservationForLocationTimeDto locationTime,
                                     BindingResult bindingResult,
                                     Model model,
                                     HttpSession session) {
        if (bindingResult.hasErrors()) {
            System.out.println("FAAAAAAAAAAIL");
            model.addAttribute("offices", officeService.getOffices());
            return "index";
        } else {
            System.out.println("NO ERRORS");
        }

        RentalOffice pickup = officeService.getOffice(locationTime.getPickup());
        ReservationDto reservation = new ReservationDto();
        reservation.setPickup(pickup);
        System.out.println(pickup);
        session.setAttribute("reservation", reservation);
        log.debug("startDate: {}", locationTime.getStartDate());
        log.debug("endDate: {}", locationTime.getEndDate());

        session.setAttribute("locationTime", locationTime);
        return "redirect:/reservation/offerselect";
    }
}
