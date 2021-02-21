package no.hvl.dat109.bilutleie.controller;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.dto.ReservationDto;
import no.hvl.dat109.bilutleie.dto.ReservationForLocationTimeDto;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import no.hvl.dat109.bilutleie.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Slf4j
@RequestMapping("/reservation")
@Controller
public class ReservationFormController {

    private final CarService carService;
    private final RentalOfficeService officeService;
    private final ModelMapper mapper;

    public ReservationFormController(CarService carService, RentalOfficeService officeService, ReservationService reservationService, ModelMapper mapper) {
        this.carService = carService;
        this.officeService = officeService;
        this.mapper = mapper;
    }

    @PostMapping("/locationtime")
    public String locationTimeSubmit(@Valid @ModelAttribute("locationTime") ReservationForLocationTimeDto locationTime,
                                     BindingResult bindingResult, Model model, HttpSession session) {

        if (bindingResult.hasErrors() || !validDates(locationTime, bindingResult)) {
            model.addAttribute("offices", officeService.getOffices());
            return "index";
        }

        ReservationDto reservation = new ReservationDto();
        mapper.map(locationTime, reservation);

        session.setAttribute("reservation", reservation);
        session.setAttribute("locationTime", locationTime);
        return "redirect:/reservation/offerselect";
    }

    @GetMapping("/offerselect")
    public String offerSelectForm(HttpSession session, Model model) {

        var reservation = (ReservationDto) session.getAttribute("reservation");
        model.addAttribute("categories", carService.availableCategories(reservation.getPickup(),
                reservation.getStartDate(), reservation.getEndDate()));
        model.addAttribute("city", reservation.getPickup().getAddress().getCity());
        model.addAttribute("cars", carService.getCarsByOffice(reservation.getPickup()));
        return "offerselect";
    }

    @PostMapping("/offerselect")
    public String selectOffer(@RequestParam String category, HttpSession session) {
        var reservation = (ReservationDto) session.getAttribute("reservation");
        reservation.setCarCategory(CarCategory.valueOf(category));
        return "redirect:/reservation/details";
    }

    @GetMapping("/details")
    public String detailsForm(Model model, HttpSession session) {
        model.addAttribute("reservation", (ReservationDto) session.getAttribute("reservation"));
        model.addAttribute("customerDetails", new CustomerForDetailsDto());
        return "details";
    }

    private boolean validDates(ReservationForLocationTimeDto locationTime, BindingResult bindingResult) {
        if (locationTime.getStartDate().isBefore(locationTime.getEndDate())) return true;
        bindingResult.addError(new FieldError("locationTime", "endDate",
                "Leveringstid må være etter utlevering"));
        return false;
    }
}
