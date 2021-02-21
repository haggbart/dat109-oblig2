package no.hvl.dat109.bilutleie.controller;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.dto.ReservationDto;
import no.hvl.dat109.bilutleie.model.Customer;
import no.hvl.dat109.bilutleie.model.Address;
import no.hvl.dat109.bilutleie.model.Reservation;
import no.hvl.dat109.bilutleie.service.CustomerService;
import no.hvl.dat109.bilutleie.service.ReservationService;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final ModelMapper modelMapper;
    private final ReservationService reservationService;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper, ReservationService reservationService) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
        this.reservationService = reservationService;
    }

    //  fra getmapping("/details")       model.addAttribute("details", new CustomerForDetailsDto());
    @PostMapping
    public String createCustomer(@Valid @ModelAttribute("customerDetails") CustomerForDetailsDto customerDetails, BindingResult bindingResult, Model model, HttpSession session) {

        String forename = customerDetails.getForename();
        String surname = customerDetails.getSurname();

        Address address = new Address("Nobelsgata 18", 2719, "Oslo");

        log.debug("forname: {}", forename);
        log.debug("surname: {}", surname);

        Customer customer = new Customer(forename, surname, address);

        var reservationDto = (ReservationDto) session.getAttribute("reservation");
        Reservation reservation = new Reservation();

        modelMapper.map(reservationDto, reservation);
        log.debug("ReservationDto: {}", reservationDto);

        // TODO: DO NOT DELETE
        reservation.setCar(null);
        log.debug("Reservation: {}", reservation);

        customer = customerService.save(customer);

        reservation.setCustomer(customer);

        reservationService.save(reservation);


        return "finish";
    }

    @GetMapping
    public String test() {
        return "finish";
    }

    /*
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
     */

}
