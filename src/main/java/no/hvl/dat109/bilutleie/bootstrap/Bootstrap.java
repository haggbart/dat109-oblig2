package no.hvl.dat109.bilutleie.bootstrap;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.Person;
import no.hvl.dat109.bilutleie.service.PersonService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@Component
public class Bootstrap {

    private final PersonService personService;
    private final CarBootstrap carBootstrap;
    private final RentalOfficeBootstrap rentalOfficeBootstrap;

    public Bootstrap(PersonService personService, CarBootstrap carBootstrap, RentalOfficeBootstrap rentalOfficeBootstrap) {
        this.personService = personService;
        this.carBootstrap = carBootstrap;
        this.rentalOfficeBootstrap = rentalOfficeBootstrap;
    }

    @PostConstruct
    private void init() {

        Person p1 = new Person("Atle Patle");
        Person p2 = new Person("Per Viskelær");

        personService.save(p1);
        personService.save(p2);




        List<Person> persons = personService.getPersons();
        persons.forEach(System.out::println);


        Person per = personService.findFirst("per");
        System.out.println(per);



        rentalOfficeBootstrap.createOffices();
        carBootstrap.createCars();


        log.info("Bootstrap complete");
    }
}
