package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.Data;

@Data
public class CarDO {

    private Integer id;
    private String make;
    private String model;
    private Integer year;
    private Integer mileage;
    private String fuel;
    private String carClass;
    private String status;
    private String registrationNumber;
}
