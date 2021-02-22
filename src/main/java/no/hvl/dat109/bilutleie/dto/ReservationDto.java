package no.hvl.dat109.bilutleie.dto;

import lombok.Data;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.Customer;
import no.hvl.dat109.bilutleie.model.RentalOffice;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class ReservationDto {

    private Long endMilage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private CarCategory carCategory;

    private Customer customer;

    private RentalOffice pickup;

    private RentalOffice dropoff;

    public Long days() {
        return Math.max(Duration.between(startDate, endDate).toDays(), 1);
    }
}
