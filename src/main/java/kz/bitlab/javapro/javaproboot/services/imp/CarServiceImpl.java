package kz.bitlab.javapro.javaproboot.services.imp;

import kz.bitlab.javapro.javaproboot.entities.Car;
import kz.bitlab.javapro.javaproboot.entities.Category;
import kz.bitlab.javapro.javaproboot.entities.Country;
import kz.bitlab.javapro.javaproboot.repository.CarRepository;
import kz.bitlab.javapro.javaproboot.repository.CategoryRepository;
import kz.bitlab.javapro.javaproboot.repository.CountryRepository;
import kz.bitlab.javapro.javaproboot.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        //return carRepository.findAll();
        return  carRepository.findAllByEngineVolumeGreaterThanOrderByPriceDesc(0);
    }

    @Override
    public Car getCar(Long id) {
        //return carRepository.getReferenceById(id);
        return carRepository.findCarByIdAndEngineVolumeGreaterThan(id, 0);
    }

    @Override
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country getCountry(Long id) {
        return countryRepository.getReferenceById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.getReferenceById(id);
    }
}