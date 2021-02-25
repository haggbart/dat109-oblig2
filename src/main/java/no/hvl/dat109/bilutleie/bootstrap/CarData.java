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

            //    public Car(String vin, Long mileage, String brand, String color, CarCategory category, RentalOffice rentalOffice) {
            add(new Car("NT 1337", 1400000, "BMW", "Svart", CarCategory.B));
            add(new Car("DE 313", 43000, "Porshe", "Blå", CarCategory.A));
            add(new Car("FE 500", 250000, "Ford", "Rød", CarCategory.C));
            add(new Car("NO 884500", 20000, "Ford", "Rød", CarCategory.C));
            add(new Car("NO 566465", 988500, "Ford", "Gul", CarCategory.D));
            add(new Car("NO 588998", 488500, "Ford", "Grønn", CarCategory.A));
            add(new Car("NO 562154", 589900, "Ford", "Hvit", CarCategory.B));
        }};
        cars1.forEach(c -> c.setRentalOffice(officeService.getOffices().get(0)));
        cars1.forEach(carService::save);

        // add cars office2
        List<Car> cars2 = new ArrayList<>() {{
            add(new Car("AB 1332", 90400, "Opel", "Blå", CarCategory.B));
            add(new Car("NO 232", 59000, "Opel", "Rød", CarCategory.A));
        }};
        cars2.forEach(c -> c.setRentalOffice(officeService.getOffices().get(1)));
        cars2.forEach(carService::save);
    }
}
