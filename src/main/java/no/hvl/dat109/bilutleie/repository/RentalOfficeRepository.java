package no.hvl.dat109.bilutleie.repository;

import no.hvl.dat109.bilutleie.model.RentalOffice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalOfficeRepository extends CrudRepository<RentalOffice, Long> {

    List<RentalOffice> findAll();
}
