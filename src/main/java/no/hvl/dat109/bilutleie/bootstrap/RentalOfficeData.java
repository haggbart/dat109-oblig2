package no.hvl.dat109.bilutleie.bootstrap;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.Address;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.repository.CarRepository;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RentalOfficeData {

    private final RentalOfficeService officeService;
    private final CarRepository carRepository;

    public RentalOfficeData(RentalOfficeService officeService, CarRepository carRepository) {
        this.officeService = officeService;
        this.carRepository = carRepository;
    }

    public void createOffices() {

        List<RentalOffice> offices = new ArrayList<>() { {
            add(new RentalOffice("90808874", new Address("Test", 1337, "Førde")));
            add(new RentalOffice("90442211", new Address("Gata 21", 1222, "Oslo")));
        }};

        offices.forEach(officeService::save);

        log.debug("test debug ....");
    }
}
