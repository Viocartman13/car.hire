package ro.agilehub.javacourse.car.hire.fleet.service.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

@Mapper(componentModel = "spring")
public interface CarMapperService {

    Car toEntity(CarDO carDO);

    CarDO toDomainObject(Car car);
}
