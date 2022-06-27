package kz.bitlab.javapro.javaproboot.controllers;

import kz.bitlab.javapro.javaproboot.model.Car;
import kz.bitlab.javapro.javaproboot.services.CarService;
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
    private CarService carService;

    @GetMapping(value = "/")
    public String indexPage(Model model){
        List<Car> cars = carService.getAllCars();
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

        carService.addCar(new Car(null,name,model, price, engine_volume));
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name="id") Long id,
                          Model model){
        Car car = carService.getCar(id);
        model.addAttribute("car", car);
        return "details";
    }

    @PostMapping(value = "/savecar")
    public String saveCar(@RequestParam(name = "car_id") Long id,
                         @RequestParam(name = "car_name") String name,
                         @RequestParam(name = "car_model") String model,
                         @RequestParam(name = "car_price") int price,
                         @RequestParam(name = "car_engine_volume") double engine_volume){

        Car car = carService.getCar(id);

        if (car != null){
            car.setName(name);
            car.setModel(model);
            car.setPrice(price);
            car.setEngineVolume(engine_volume);
            carService.saveCar(car);
        };
        return "redirect:/";
    }

    @PostMapping(value = "/deletecar")
    public String deleteCar(@RequestParam(name = "car_id") Long id){

        Car car = carService.getCar(id);

        if (car != null){
            carService.deleteCar(car);
        }
        return "redirect:/";
    }
}