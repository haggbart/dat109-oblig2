package no.hvl.dat109.bilutleie.repository;

import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

//    @Query("select c from Car where c.mile = :category")
//    List<Car> findCars


    List<Car> findAllByCategoryEquals(CarCategory category);

    List<Car> findCarsByRentalOffice(RentalOffice office);

    Car findFirstCarByRentalOfficeAndCategoryEquals(RentalOffice office, CarCategory category);
}
