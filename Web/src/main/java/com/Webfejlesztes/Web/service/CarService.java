package com.Webfejlesztes.Web.service;

import com.Webfejlesztes.Web.exception.ResourceNotFoundException;
import com.Webfejlesztes.Web.model.Car;
import com.Webfejlesztes.Web.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired private CarRepository carRepository;

    public List<Car> listAll() {
        return (List<Car>) carRepository.findAll();
    }

    public void save(Car car){ carRepository.save(car); }
    public Car get(Long id) throws ResourceNotFoundException {
        Optional<Car> result=carRepository.findById(id);
        if (result.isPresent()){
            return result.get();
        }

        throw new ResourceNotFoundException("Nem található autó ilyen ID-vel" + id);
    }

    public void delete(Long id) throws ResourceNotFoundException {
        Long count = carRepository.countById(id);
        if(count == null || count == 0){
            throw new ResourceNotFoundException("Nem található autó ilyen ID-vel" + id);
        }
        carRepository.deleteById(id);
    }

}
