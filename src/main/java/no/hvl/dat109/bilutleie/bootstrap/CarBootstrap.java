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

        RentalOffice rentalOffice = officeService.getOffice(1);
        Car lambo = new Car("NT 1337", CarCategory.B);
        Car golf = new Car("DE 313", CarCategory.A);
        Car varebil = new Car("FE 500", CarCategory.C);

        lambo.setRentalOffice(rentalOffice);
        golf.setRentalOffice(rentalOffice);
        varebil.setRentalOffice(rentalOffice);

        carService.save(lambo);
        carService.save(golf);
        carService.save(varebil);

        RentalOffice rentalOffice1 = officeService.getOffice(2);
        Car car1 = new Car("AB 1332", CarCategory.B);
        Car car2 = new Car("NO 232", CarCategory.B);
        car1.setRentalOffice(rentalOffice1);
        car2.setRentalOffice(rentalOffice1);
        carService.save(car1);
        carService.save(car2);
    }
}
