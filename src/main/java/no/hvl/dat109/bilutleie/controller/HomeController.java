package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.service.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final GreetingService greetingService;

    public HomeController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    public String sayHello(Model model) {
        model.addAttribute("greeting", greetingService.greet());
        return "index";
    }

    @GetMapping("/{name}")
    public String sayHello(Model model, @PathVariable String name) {
        model.addAttribute("greeting", greetingService.greet(name));
        return "index";
    }
}
