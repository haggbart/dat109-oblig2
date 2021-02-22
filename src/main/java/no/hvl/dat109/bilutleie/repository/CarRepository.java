package no.hvl.dat109.bilutleie.repository;

import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

    Car findFirstCarByRentalOfficeAndCategoryEquals(RentalOffice office, CarCategory category);
}
