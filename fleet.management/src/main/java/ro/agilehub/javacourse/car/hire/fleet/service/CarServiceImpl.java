package ro.agilehub.javacourse.car.hire.fleet.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.mapper.CarMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private CarMapper carMapper;

    @Override
    public List<CarDO> findAll() {
        return carRepository.findAll()
                .stream()
                .map(this::mapToDO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDO findById(String id) {
        return carRepository.findById(new ObjectId(id))
                .map(this::mapToDO)
                .orElseThrow();
    }

    @Override
    public void deleteById(String id) {
        carRepository.deleteById(new ObjectId(id));
    }

    @Override
    public CarDO addCar(CarDO carDO) {
        Car savedCar = carRepository.save(mapToEntity(carDO));
        return mapToDO(savedCar);
    }

    private CarDO mapToDO(Car car) {
        return carMapper.toDomainObject(car);
    }

    private Car mapToEntity(CarDO carDO) {
        return carMapper.toEntity(carDO);
    }
}
