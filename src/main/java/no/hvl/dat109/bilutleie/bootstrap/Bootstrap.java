package no.hvl.dat109.bilutleie.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Component
public class Bootstrap {

    private final RentalOfficeData rentalOfficeData;
    private final CarData carData;
    private final CustomerData customerData;

    public Bootstrap(RentalOfficeData rentalOfficeData, CarData carData, CustomerData customerData) {
        this.rentalOfficeData = rentalOfficeData;
        this.carData = carData;
        this.customerData = customerData;
    }

    @PostConstruct
    private void init() {

        rentalOfficeData.createOffices();
        carData.createCars();
        customerData.createCustomers();

        log.info("Bootstrap complete");
    }
}
