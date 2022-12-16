package com.Webfejlesztes.Web.controller;

import com.Webfejlesztes.Web.exception.ResourceNotFoundException;
import com.Webfejlesztes.Web.model.Car;
import com.Webfejlesztes.Web.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarRestController {

        @Autowired
        private CarService service;

        @GetMapping
        public List<Car> getAllCars(){
            return service.listAll();
        }

        @PostMapping
        public void createCar(@RequestBody Car car){
            service.save(car);
        }

        @GetMapping("{id}")
        public ResponseEntity<Car> getCarById(@PathVariable long id){
            Car car;
            try {
                car = service.get(id);
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
                throw new ResourceNotFoundException("Nincs ilyen autó ezzel az id-vel" + id);
            }
            return ResponseEntity.ok(car);
        }


        @PutMapping("{id}")
        public ResponseEntity<Car> updateCar(@PathVariable long id,@RequestBody Car carDetails){
            Car updateCar;
            try {
                updateCar = service.get(id);
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
                throw new ResourceNotFoundException("Nincs ilyen autó ezzel az id-vel" + id);
            }

            updateCar.setCarType(carDetails.getCarType());
            updateCar.setCarLicense(carDetails.getCarLicense());
            updateCar.setCarColor(carDetails.getCarColor());

            service.save(updateCar);

            return ResponseEntity.ok(updateCar);
        }

        @DeleteMapping("{id}")
        public ResponseEntity<HttpStatus> deleteCar(@PathVariable long id){
            try {
                service.delete(id);
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
                throw new ResourceNotFoundException("Nincs ilyen autó ezzel az id-vel" + id);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

}

