package no.hvl.dat109.bilutleie.controller;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.dto.CustomerForDetailsDto;
import no.hvl.dat109.bilutleie.dto.ReservationDto;
import no.hvl.dat109.bilutleie.service.CustomerService;
import no.hvl.dat109.bilutleie.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
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
    private final ModelMapper mapper;
    private final ReservationService reservationService;

    public CustomerController(CustomerService customerService, ModelMapper mapper, ReservationService reservationService) {
        this.customerService = customerService;
        this.mapper = mapper;
        this.reservationService = reservationService;
    }

    @PostMapping
    public String createCustomer(@Valid @ModelAttribute("customerDetails") CustomerForDetailsDto customerDetails, BindingResult bindingResult, HttpSession session) {

        if (bindingResult.hasErrors()) {
            log.debug("failed validation of customerDetails");
            return "details";
        }

        log.debug("Customer: {}", customerDetails);
        var reservationDto = (ReservationDto) session.getAttribute("reservation");
        reservationService.add(customerDetails, reservationDto);

        return "finish";
    }

    @GetMapping
    public String test() {
        return "finish";
    }
}
