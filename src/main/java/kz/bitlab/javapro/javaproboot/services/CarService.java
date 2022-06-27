package kz.bitlab.javapro.javaproboot.services;

import kz.bitlab.javapro.javaproboot.model.Car;

import java.util.List;

public interface CarService {

    Car addCar(Car car);
    List<Car> getAllCars();
    Car getCar(Long id);
    void deleteCar(Car car);
    Car saveCar (Car car);
}
