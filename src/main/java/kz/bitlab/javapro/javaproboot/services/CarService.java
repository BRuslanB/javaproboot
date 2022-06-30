package kz.bitlab.javapro.javaproboot.services;

import kz.bitlab.javapro.javaproboot.entities.Car;
import kz.bitlab.javapro.javaproboot.entities.Category;
import kz.bitlab.javapro.javaproboot.entities.Country;

import java.util.List;

public interface CarService {

    Car addCar(Car car);
    List<Car> getAllCars();
    Car getCar(Long id);
    void deleteCar(Car car);
    Car saveCar (Car car);

    List<Country> getAllCountries();
    Country addCountry(Country country);
    Country saveCountry(Country country);
    Country getCountry(Long id);

    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category saveCategory(Category category);
    Category getCategory(Long id);
}