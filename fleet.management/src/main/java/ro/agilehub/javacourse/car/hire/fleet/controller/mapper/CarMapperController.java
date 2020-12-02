package ro.agilehub.javacourse.car.hire.fleet.controller.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;

@Mapper(componentModel = "spring", uses = MakeMapperController.class)
public interface CarMapperController {

    CarDO dtoToDomainObject(CarDTO carDTO);

    CarDTO domainObjectToDTO(CarDO carDO);

}
