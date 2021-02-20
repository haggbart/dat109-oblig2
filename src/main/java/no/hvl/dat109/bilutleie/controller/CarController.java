package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.service.CarService;
import org.springframework.stereotype.Controller;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /*
    kommer til å bli brukt på adminsiden
     */
}
