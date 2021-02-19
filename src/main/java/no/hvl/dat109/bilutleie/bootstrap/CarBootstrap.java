package no.hvl.dat109.bilutleie.bootstrap;

import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Component;

@Component
public class CarBootstrap {

    private final CarService carService;
    private final RentalOfficeService officeService;

    public CarBootstrap(CarService carService, RentalOfficeService officeService) {
        this.carService = carService;
        this.officeService = officeService;
    }

    public void createCars() {

        Car lambo = new Car(1337L, CarCategory.B);
        Car golf = new Car(313L, CarCategory.A);
        Car varebil = new Car(500L, CarCategory.C);

        RentalOffice rentalOffice = officeService.getOffices().get(0);

        lambo.setRentalOffice(rentalOffice);
        golf.setRentalOffice(rentalOffice);
        varebil.setRentalOffice(rentalOffice);

        carService.save(lambo);
        carService.save(golf);
        carService.save(varebil);
    }
}
