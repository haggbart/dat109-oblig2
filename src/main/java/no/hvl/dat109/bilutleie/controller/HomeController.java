package no.hvl.dat109.bilutleie.controller;

import no.hvl.dat109.bilutleie.service.GreetingService;
import no.hvl.dat109.bilutleie.service.JokeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final GreetingService greetingService;
    private final JokeService jokeService;

    public HomeController(GreetingService greetingService, JokeService jokeService) {
        this.greetingService = greetingService;
        this.jokeService = jokeService;
    }

    @GetMapping("/")
    public String sayHello(Model model) {
        model.addAttribute("greeting", greetingService.greet());
        model.addAttribute("joke", jokeService.getRandomJoke());
        return "index";
    }

    @GetMapping("/{name}")
    public String sayHello(Model model, @PathVariable String name) {
        model.addAttribute("greeting", greetingService.greet(name));
        model.addAttribute("joke", jokeService.getRandomJoke());
        return "index";
    }
}
