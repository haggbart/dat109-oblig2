package no.hvl.dat109.bilutleie.dto;

import lombok.Data;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.Customer;
import no.hvl.dat109.bilutleie.model.RentalOffice;

import java.time.LocalDateTime;

@Data
public class ReservationDto {

    private long endMilage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private CarCategory carCategory;

    private Customer customer;

    private RentalOffice pickup;

    private RentalOffice dropoff;
}
