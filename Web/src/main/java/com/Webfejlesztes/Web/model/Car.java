package com.Webfejlesztes.Web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255, name = "car_type")
    private String CarType;

    @Column(nullable = false, length = 11, name = "license")
    private String CarLicense;

    @Column(nullable = false, length = 25, name = "color")
    private String CarColor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String car_type) {
        CarType = car_type;
    }

    public String getCarLicense() {
        return CarLicense;
    }

    public void setCarLicense(String license) {
        CarLicense = license;
    }

    public String getCarColor() {
        return CarColor;
    }

    public void setCarColor(String color) {
        CarColor = color;
    }
}
