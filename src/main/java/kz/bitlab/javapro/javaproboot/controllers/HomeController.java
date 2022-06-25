package kz.bitlab.javapro.javaproboot.controllers;

import kz.bitlab.javapro.javaproboot.model.Car;
import kz.bitlab.javapro.javaproboot.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping(value = "/")
    public String indexPage(Model model){
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "indexpage";
    }

    @GetMapping(value = "/addcar")
    public String addCar(Model model){
        return "addcar";
    }

    @PostMapping(value = "/addcar")
    public String addCar(@RequestParam(name = "car_name") String name,
                         @RequestParam(name = "car_model") String model,
                         @RequestParam(name = "car_price") int price,
                         @RequestParam(name = "car_engine_volume") double engine_volume){

        Car car = new Car();
        car.setName(name);
        car.setModel(model);
        car.setPrice(price);
        car.setEngineVolume(engine_volume);

        carRepository.save(car);
        return "redirect:/addcar";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name="id") Long id,
                          Model model){
        Car car = carRepository.getReferenceById(id);
        model.addAttribute("car", car);
        return "details";
    }

    @PostMapping(value = "/savecar")
    public String saveCar(@RequestParam(name = "car_id") Long id,
                         @RequestParam(name = "car_name") String name,
                         @RequestParam(name = "car_model") String model,
                         @RequestParam(name = "car_price") int price,
                         @RequestParam(name = "car_engine_volume") double engine_volume){

        Car car = carRepository.findById(id).orElse(null);
        if (car != null){
            car.setName(name);
            car.setModel(model);
            car.setPrice(price);
            car.setEngineVolume(engine_volume);

            carRepository.save(car);
            return "redirect:/details/" + id;
        };

        return "redirect:/";
    }

    @PostMapping(value = "/deletecar")
    public String deleteCar(@RequestParam(name = "car_id") Long id){

        carRepository.deleteById(id);
        return "redirect:/";
    }
}