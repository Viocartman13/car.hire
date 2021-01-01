package ro.agilehub.javacourse.car.hire.fleet.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = "id")
@Document("car")
@Builder
public class Car {

    @Id
    @Field("_id")
    private ObjectId id;

    @DBRef
    private Make make;
    private String model;
    private LocalDate year;
    private Integer mileage;
    private String fuel;
    private String carClass;
    private String status;
    private String registrationNumber;

}
