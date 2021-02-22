package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.model.RentalOffice;

import java.util.List;

public interface RentalOfficeService {

    void save(RentalOffice rentalOffice);

    List<RentalOffice> getOffices();

    RentalOffice getOffice(Long id);
}
