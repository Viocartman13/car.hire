package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CarDO {

    private String id;
    private MakeDO make;
    private String model;
    private LocalDate year;
    private Integer mileage;
    private String fuel;
    private String carClass;
    private String status;
    private String registrationNumber;
}
