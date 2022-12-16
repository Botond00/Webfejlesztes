package com.Webfejlesztes.Web.repository;

import com.Webfejlesztes.Web.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

    public long countById(long id);
}
