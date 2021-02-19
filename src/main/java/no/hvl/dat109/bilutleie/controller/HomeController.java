package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.service.GreetingService;
import no.hvl.dat109.bilutleie.service.JokeService;
import no.hvl.dat109.bilutleie.service.PersonService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final GreetingService greetingService;
    private final JokeService jokeService;
    private final PersonService personService;
    private final RentalOfficeService officeService;

    public HomeController(GreetingService greetingService, JokeService jokeService, PersonService personService, RentalOfficeService officeService) {
        this.greetingService = greetingService;
        this.jokeService = jokeService;
        this.personService = personService;
        this.officeService = officeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("offices", officeService.getOffices());
        return "index";
    }

//    @GetMapping("/{name}")
//    public String sayHello(Model model, @PathVariable String name) {
//        model.addAttribute("greeting", greetingService.greet(name));
//        model.addAttribute("joke", jokeService.getRandomJoke());
//        return "index";
//    }
}
