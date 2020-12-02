package ro.agilehub.javacourse.car.hire.fleet.controller.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.api.model.MakeDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;

@Mapper(componentModel = "spring")
public interface MakeMapperController {

    MakeDO dtoToDomainObject(MakeDTO makeDTO);

    MakeDTO domainObjectToDTO(MakeDO makeDO);
}
