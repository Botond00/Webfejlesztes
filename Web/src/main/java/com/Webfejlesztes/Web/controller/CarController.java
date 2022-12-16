package com.Webfejlesztes.Web.controller;

import com.Webfejlesztes.Web.exception.ResourceNotFoundException;
import com.Webfejlesztes.Web.model.Car;
import com.Webfejlesztes.Web.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping("/cars")
    public String showCarList(Model model){
        List<Car> listCars = service.listAll();
        model.addAttribute("listCar", listCars);

        return "cars";
    }
    @GetMapping("/cars/new")
    public String showNewForm(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("pageTitle", "ÚJ autó felvétele");
        return "car_page";
    }

    @PostMapping("/car/save")
    public String saveCar(Car car){
        service.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        try {
            Car car = service.get(id);
            model.addAttribute("car", car);
            model.addAttribute("pageTitle", "Szerkesztés");
            return "car_page";
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return "redirect:/cars";
        }

    }

    @GetMapping("/cars/delete/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        try {
            service.delete(id);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/cars";
    }

}