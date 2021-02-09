package no.hvl.dat109.bilutleie.service;


import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String greet() {
        return "Heisann hoppsann";
    }

    public String greet(String name) {
        return "Hei " + name;
    }
}
