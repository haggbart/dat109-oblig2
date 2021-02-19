package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
public class ReservationController {

    private final CarService carService;

    public ReservationController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("offerselect")
    public String showOffers(HttpSession session, Model model) {

        System.out.println("session: " + session.getAttribute("test"));
        return "offerselect";
    }

    @PostMapping("/offerselect")
    public String selectCarCategory(HttpSession session) {
        session.setAttribute("test", "hoi");
        return "redirect:/offerselect";
    }
}
