package no.hvl.dat109.bilutleie.bootstrap;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.*;
import no.hvl.dat109.bilutleie.service.CarService;
import no.hvl.dat109.bilutleie.service.CustomerService;
import no.hvl.dat109.bilutleie.service.RentalOfficeService;
import no.hvl.dat109.bilutleie.service.ReservationService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CustomerData {

    private final CustomerService customerService;
    private final RentalOfficeService officeService;
    private final ReservationService reservationService;
    private final CarService carService;

    public CustomerData(CustomerService customerService, RentalOfficeService officeService, ReservationService reservationService, CarService carService) {
        this.customerService = customerService;
        this.officeService = officeService;
        this.reservationService = reservationService;
        this.carService = carService;
    }

    public void createCustomers() {
        List<Customer> customers = new ArrayList<>() {{
            add(new Customer("Atle Patle", new Address("Epic street 1", 2222, "Oslo")));
            add(new Customer("Per Viskelær", new Address("Gulehagen 22", 3333, "Bergen")));
        }};
        customers.forEach(customerService::save);

        // add some reservations
        // (LocalDateTime startDate, LocalDateTime endDate, CarCategory carCategory, Customer customer,
        //                       RentalOffice pickup, RentalOffice dropoff) {

        LocalDateTime now = LocalDateTime.now();

        // Førde
        RentalOffice forde = officeService.getOffices().get(0);
        List<Reservation> reservations = new ArrayList<>() {{
            add(new Reservation(now, now.plusDays(4), CarCategory.A, customers.get(0), forde, forde));
            add(new Reservation(now.plusDays(10), now.plusDays(14), CarCategory.B, customers.get(0), forde, forde));
            add(new Reservation(now.minusDays(30), now.plusDays(14), CarCategory.C, customers.get(0), forde, forde));
        }};
        reservations.forEach(reservationService::save);

        // some testing
        carService.availableCategories(forde, now.plusDays(20), now.plusDays(24));
        carService.availableCategories(forde, now, now.plusDays(24));
        carService.availableCategories(forde, now.minusDays(16), now.plusDays(24));
        carService.availableCategories(forde, now.minusDays(16), now.minusDays(2));
        System.out.println(now);
        System.out.println(forde);
    }
}
