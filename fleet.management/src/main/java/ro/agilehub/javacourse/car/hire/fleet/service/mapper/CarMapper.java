package ro.agilehub.javacourse.car.hire.fleet.service.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Car;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface CarMapper {

    Car toEntity(CarDO carDO);

    CarDO toDomainObject(Car car);

    CarDO dtoToDomainObject(CarDTO carDTO);

    CarDTO domainObjectToDTO(CarDO carDO);

    CarDTO entityToDTO(Car car);
}
