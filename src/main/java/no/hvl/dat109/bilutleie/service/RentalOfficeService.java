package no.hvl.dat109.bilutleie.service;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.repository.RentalOfficeRepository;
import no.hvl.dat109.bilutleie.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
