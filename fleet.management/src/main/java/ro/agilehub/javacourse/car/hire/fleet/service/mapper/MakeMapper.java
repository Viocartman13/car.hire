package ro.agilehub.javacourse.car.hire.fleet.service.mapper;

import org.mapstruct.Mapper;
import ro.agilehub.javacourse.car.hire.fleet.domain.MakeDO;
import ro.agilehub.javacourse.car.hire.fleet.entity.Make;

@Mapper(componentModel = "spring", uses = ObjectIdMapper.class)
public interface MakeMapper {

    Make toEntity(MakeDO makeDO);

    MakeDO toDomainObject(Make make);
}
