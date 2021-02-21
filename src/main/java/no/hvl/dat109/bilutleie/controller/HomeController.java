package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.dto.ReservationForLocationTimeDto;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final RentalOfficeService officeService;

    public HomeController(RentalOfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("offices", officeService.getOffices());
        model.addAttribute("locationTime", new ReservationForLocationTimeDto());
        return "index";
    }
}
