package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.model.*;

import java.time.LocalDateTime;
import java.util.List;

public interface CarService {

    void save(Car car);

    Car getAvailable(RentalOffice office, CarCategory category);

    Car getAvailable(Reservation reservation);

    List<Car> getCars();

    List<Offer> availableCategories(RentalOffice office, LocalDateTime start, LocalDateTime end);

    Integer updateMileage(Reservation reservation, Integer mileage);
}
