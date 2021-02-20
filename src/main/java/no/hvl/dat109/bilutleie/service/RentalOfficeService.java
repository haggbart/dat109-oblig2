package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.repository.RentalOfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalOfficeService {

    private final RentalOfficeRepository officeRepository;

    public RentalOfficeService(RentalOfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
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
