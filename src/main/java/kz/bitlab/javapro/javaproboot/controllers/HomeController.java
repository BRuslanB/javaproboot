package kz.bitlab.javapro.javaproboot.controllers;

import kz.bitlab.javapro.javaproboot.entities.Car;
import kz.bitlab.javapro.javaproboot.entities.Category;
import kz.bitlab.javapro.javaproboot.entities.Country;
import kz.bitlab.javapro.javaproboot.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/")
    public String indexPage(Model model){

        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);

        List<Country> countries = carService.getAllCountries();
        model.addAttribute("countries", countries);

        return "indexpage";
    }

    @GetMapping(value = "/addcar")
    public String addCar(Model model){

        List<Country> countries = carService.getAllCountries();
        model.addAttribute("countries", countries);

        return "addcar";
    }

    @PostMapping(value = "/addcar")
    public String addCar(@RequestParam(name = "car_country_id") Long country_id,
                         @RequestParam(name = "car_name") String name,
                         @RequestParam(name = "car_model") String model,
                         @RequestParam(name = "car_price") int price,
                         @RequestParam(name = "car_engine_volume") double engine_volume){

        Country cnt = carService.getCountry(country_id);
        if (cnt != null) {
            Car car = new Car();
            car.setName(name);
            car.setModel(model);
            car.setPrice(price);
            car.setEngineVolume(engine_volume);
            car.setCountry(cnt);
            carService.addCar(car);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name="id") Long id, Model model){

        Car car = carService.getCar(id);
        model.addAttribute("car", car);

        List<Country> countries = carService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Category> categories = carService.getAllCategories();
        model.addAttribute("categories", categories);

        if (car.getCategories()!=null){
            categories.removeAll(car.getCategories());
        }

        return "details";
    }

    @PostMapping(value = "/savecar")
    public String saveCar(@RequestParam(name = "car_id") Long id,
                          @RequestParam(name = "car_country_id") Long country_id,
                          @RequestParam(name = "car_name") String name,
                          @RequestParam(name = "car_model") String model,
                          @RequestParam(name = "car_price") int price,
                          @RequestParam(name = "car_engine_volume") double engine_volume){

        Car car = carService.getCar(id);

        if (car != null){

            Country cnt = carService.getCountry(country_id);
            if (cnt != null) {
                car.setName(name);
                car.setModel(model);
                car.setPrice(price);
                car.setEngineVolume(engine_volume);
                car.setCountry(cnt);
                carService.saveCar(car);
            }
        }
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

    @PostMapping(value = "/assigncategory")
    public String assignCategory(@RequestParam(name = "car_id") Long id,
                                 @RequestParam(name = "car_category_id") Long category_id){

        Category cat = carService.getCategory(category_id);
        if (cat != null){
            Car car = carService.getCar(id);
            if (car != null){
                List<Category> categories = car.getCategories();
                if (categories == null){
                     categories = new ArrayList<>();
                }
                categories.add(cat);

                carService.saveCar(car);
                return "redirect:/details/" + id;
            }
        }
        return "redirect:/";
    }

    @PostMapping(value = "/reassigncategory")
    public String reAssignCategory(@RequestParam(name = "car_id") Long id,
                                 @RequestParam(name = "car_category_id") Long category_id){

        Category cat = carService.getCategory(category_id);
        if (cat != null){
            Car car = carService.getCar(id);
            if (car != null){
                List<Category> categories = car.getCategories();
                if (categories != null){
                    categories.remove(cat);
                }

                carService.saveCar(car);
                return "redirect:/details/" + id;
            }
        }
        return "redirect:/";
    }
}