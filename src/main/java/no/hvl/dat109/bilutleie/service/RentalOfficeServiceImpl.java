package no.hvl.dat109.bilutleie.service;

import lombok.extern.slf4j.Slf4j;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.repository.RentalOfficeRepository;
import no.hvl.dat109.bilutleie.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RentalOfficeServiceImpl implements RentalOfficeService {

    private final RentalOfficeRepository officeRepository;
    private final ReservationRepository reservationRepository;


    public RentalOfficeServiceImpl(RentalOfficeRepository officeRepository, ReservationRepository reservationRepository) {
        this.officeRepository = officeRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void save(RentalOffice rentalOffice) {
        officeRepository.save(rentalOffice);
    }

    @Override
    public List<RentalOffice> getOffices() {
        return officeRepository.findAll();
    }

    @Override
    public RentalOffice getOffice(Long id) {
        return officeRepository.findById(id).orElse(null);
    }
}
