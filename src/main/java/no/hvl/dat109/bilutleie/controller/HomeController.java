package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.dto.ReservationForLocationTimeDto;
import no.hvl.dat109.bilutleie.service.GreetingService;
import no.hvl.dat109.bilutleie.service.JokeService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final GreetingService greetingService;
    private final JokeService jokeService;
    private final RentalOfficeService officeService;

    public HomeController(GreetingService greetingService, JokeService jokeService, RentalOfficeService officeService) {
        this.greetingService = greetingService;
        this.jokeService = jokeService;
        this.officeService = officeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("offices", officeService.getOffices());
        model.addAttribute("locationTime", new ReservationForLocationTimeDto());
        return "index";
    }
}
