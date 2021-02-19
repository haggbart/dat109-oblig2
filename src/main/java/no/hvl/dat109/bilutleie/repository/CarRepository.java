package no.hvl.dat109.bilutleie.repository;

import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

//    @Query("select c from Car where c.mile = :category")
//    List<Car> findCars


    List<Car> findAllByCarCategoryEquals(@Param("category") CarCategory category);


}
