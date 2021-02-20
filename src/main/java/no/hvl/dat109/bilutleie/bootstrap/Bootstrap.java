package no.hvl.dat109.bilutleie.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class Bootstrap {

    private final CarBootstrap carBootstrap;
    private final RentalOfficeBootstrap rentalOfficeBootstrap;

    public Bootstrap(CarBootstrap carBootstrap, RentalOfficeBootstrap rentalOfficeBootstrap) {
        this.carBootstrap = carBootstrap;
        this.rentalOfficeBootstrap = rentalOfficeBootstrap;
    }

    @PostConstruct
    private void init() {

        rentalOfficeBootstrap.createOffices();
        carBootstrap.createCars();


        log.info("Bootstrap complete");
    }
}
