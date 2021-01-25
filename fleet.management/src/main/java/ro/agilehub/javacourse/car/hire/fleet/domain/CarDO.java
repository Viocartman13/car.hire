package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDO {

    private Integer id;
    private String make;
    private String model;
    private Integer year;
    private Integer mileage;
    private String fuel;
    private String carClass;
    private String status;
    private String registrationNumber;
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
