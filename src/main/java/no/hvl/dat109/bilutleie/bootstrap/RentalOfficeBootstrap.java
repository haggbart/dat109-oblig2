package no.hvl.dat109.bilutleie.bootstrap;

import no.hvl.dat109.bilutleie.model.Address;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.repository.CarRepository;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Component;

@Component
public class RentalOfficeBootstrap {

    private final RentalOfficeService officeService;
    private final CarRepository carRepository;

    public RentalOfficeBootstrap(RentalOfficeService officeService, CarRepository carRepository) {
        this.officeService = officeService;
        this.carRepository = carRepository;
    }

    public void createOffices() {


        Address testAddress = new Address("Test", 1337, "Førde");
        RentalOffice office1 = new RentalOffice("90808874", testAddress);
        RentalOffice office2 = new RentalOffice("90442211", new Address("Gata 21", 1222, "Oslo"));

        officeService.save(office1);
        officeService.save(office2);

    }
}
