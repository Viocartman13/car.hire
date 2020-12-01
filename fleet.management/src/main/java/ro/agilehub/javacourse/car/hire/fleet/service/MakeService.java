package ro.agilehub.javacourse.car.hire.fleet.service;

import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;

import java.util.List;

public interface MakeService {

    List<MakeDO> findAll();

    MakeDO findById(String id);

    void deleteById(String id);

    MakeDO addCar(MakeDO makeDO);
}
