package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String listOffers(Model model) {
        model.addAttribute("cars", carService.getCars());
        model.addAttribute("carsCategoryA", carService.getCars(CarCategory.A));
        return "offerselect";
    }

    @PostMapping("testpost")
    public String listOffers(@RequestParam String test, @RequestParam String date, Model model) {
        System.out.println("test: " + test);
        model.addAttribute("test", test);
        model.addAttribute("date", date);
        LocalDateTime dateObject = LocalDateTime.parse(date);
        System.out.print("date from object: " + dateObject);
        return "offerselect";
    }
}
