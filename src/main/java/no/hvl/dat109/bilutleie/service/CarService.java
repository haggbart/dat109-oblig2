package no.hvl.dat109.bilutleie.service;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.*;
import no.hvl.dat109.bilutleie.repository.CarRepository;
import no.hvl.dat109.bilutleie.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CarService {

    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;

    public CarService(CarRepository carRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public Car getAvailable(RentalOffice office, CarCategory category) {
        return carRepository.findFirstCarByRentalOfficeAndCategoryEquals(office, category);
    }

    public Car getAvailable(Reservation reservation) {
        return getAvailable(reservation.getPickup(), reservation.getCarCategory());
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public List<Car> getCars(CarCategory category) {
        return carRepository.findAllByCategoryEquals(category);
    }

    public List<Car> getCarsByOffice(RentalOffice office) {
        return carRepository.findCarsByRentalOffice(office);
    }

    public List<Offer> availableCategories(RentalOffice office, LocalDateTime start, LocalDateTime end) {
        int[] categoryCount = new int[CarCategory.values().length];
        for (var car : office.getCars()) {
            categoryCount[car.getCategory().ordinal()]++;
        }
        log.debug("Total cars for each category: {}", Arrays.toString(categoryCount));
        List<Reservation> reservations = reservationRepository.findReservationsByOverlap(office, start, end);
        for (var reservation : reservations) {
            categoryCount[reservation.getCarCategory().ordinal()]--;
        }
        log.debug("Available cars for each category: {}", Arrays.toString(categoryCount));

        List<Offer> availableCategories = new ArrayList<>();
        for (int i = 0; i < categoryCount.length; i++) {
            if (categoryCount[i] > 0) {
                availableCategories.add(new Offer(CarCategory.values()[i]));
            }
        }
        log.debug("Available categories: {}", availableCategories);
        return availableCategories;
    }
}
