package no.hvl.dat109.bilutleie.dto;

import lombok.Data;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.Customer;
import no.hvl.dat109.bilutleie.model.RentalOffice;

import java.util.Date;

@Data
public class ReservationDto {

    private long endMilage;
    private Date startDate;
    private Date endDate;

    private CarCategory carCategory;

    private Customer customer;

    private RentalOffice pickup;

    private RentalOffice dropoff;
}
