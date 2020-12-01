package ro.agilehub.javacourse.car.hire.fleet.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MakeDO {

    private String id;
    private String name;
    private String description;

}
