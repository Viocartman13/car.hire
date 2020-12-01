package ro.agilehub.javacourse.car.hire.fleet.service.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectIdMapper {

    default ObjectId toObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    default String toStringId(ObjectId id) {
        return id == null ? null : id.toString();
    }
}
