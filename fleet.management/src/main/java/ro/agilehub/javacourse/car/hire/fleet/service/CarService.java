package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;

import java.util.List;

public interface CarService {

    List<CarDO> findAll();

    CarDO findById(String id);

    void deleteById(String id);

    CarDO addCar(CarDO carDO);
}
