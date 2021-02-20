package no.hvl.dat109.bilutleie.bootstrap;

import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarData {

    private final CarService carService;
    private final RentalOfficeService officeService;

    public CarData(CarService carService, RentalOfficeService officeService) {
        this.carService = carService;
        this.officeService = officeService;
    }

    public void createCars() {

        // add cars office1
        List<Car> cars1 = new ArrayList<>() {{
            add(new Car("NT 1337", CarCategory.B));
            add(new Car("DE 313", CarCategory.A));
            add(new Car("FE 500", CarCategory.C));
        }};
        cars1.forEach(c -> c.setRentalOffice(officeService.getOffices().get(0)));
        cars1.forEach(carService::save);

        // add cars office2
        List<Car> cars2 = new ArrayList<>() {{
            add(new Car("AB 1332", CarCategory.B));
            add(new Car("NO 232", CarCategory.B));
        }};
        cars2.forEach(c -> c.setRentalOffice(officeService.getOffices().get(1)));
        cars2.forEach(carService::save);
    }
}
