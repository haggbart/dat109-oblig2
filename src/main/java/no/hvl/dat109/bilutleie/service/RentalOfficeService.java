package no.hvl.dat109.bilutleie.service;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.model.Reservation;
import no.hvl.dat109.bilutleie.repository.RentalOfficeRepository;
import no.hvl.dat109.bilutleie.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class RentalOfficeService {

    private final RentalOfficeRepository officeRepository;
    private final ReservationRepository reservationRepository;


    public RentalOfficeService(RentalOfficeRepository officeRepository, ReservationRepository reservationRepository) {
        this.officeRepository = officeRepository;
        this.reservationRepository = reservationRepository;
    }

    public void save(RentalOffice rentalOffice) {
        officeRepository.save(rentalOffice);
    }

    public List<RentalOffice> getOffices() {
        return officeRepository.findAll();
    }

    public RentalOffice getOffice(long id) {
        return officeRepository.findById(id).orElse(null);
    }


    public List<CarCategory> availableCategories(RentalOffice office, LocalDateTime start, LocalDateTime end) {
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

        List<CarCategory> availableCategories = new ArrayList<>();
        for (int i = 0; i < categoryCount.length; i++) {
            if (categoryCount[i] > 0) availableCategories.add(CarCategory.values()[i]);
        }
        log.debug("Available categories: {}", availableCategories);
        return availableCategories;
    }
}
