package no.hvl.dat109.bilutleie.service;

import no.hvl.dat109.bilutleie.model.Car;
import no.hvl.dat109.bilutleie.model.CarCategory;
import no.hvl.dat109.bilutleie.model.RentalOffice;
import no.hvl.dat109.bilutleie.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public List<Car> getCars(CarCategory category) {
        return carRepository.findAllByCategoryEquals(category);
    }

    public List<Car> getCarsByOffice(RentalOffice office) {
        return carRepository.findCarsByRentalOffice(office);
    }
}
