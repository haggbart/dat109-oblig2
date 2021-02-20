package no.hvl.dat109.bilutleie;

import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BilutleieApplicationTests {

    @Autowired
    private CarService carService;

    @Autowired
    private RentalOfficeService officeService;



    @Test
    void contextLoads() {
    }


    @Test
    void testCar () {
//        RentalOffice office1 = new RentalOffice("90793344", new Address());
//        officeService.save(office1);
//        Car car1 = new Car("DE 231", CarCategory.A);

        System.out.println("test start");
        List<RentalOffice> offices = officeService.getOffices();
        offices.forEach(System.out::println);
        System.out.println("test end");
    }
}
