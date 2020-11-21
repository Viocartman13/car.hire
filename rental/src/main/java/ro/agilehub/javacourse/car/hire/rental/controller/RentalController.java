package ro.agilehub.javacourse.car.hire.rental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.specification.RentalApi;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class RentalController implements RentalApi {

    private static final CarDTO car;
    private static final UserDTO user;

    static {
        car = new CarDTO();
        car.setMake("BMW");
        car.setFuel("Gasoline");
        car.setId(1);

        user = new UserDTO();
        user.setId(13);
        user.setUserName("Viocartman");
        user.setFirstName("Vio");
        user.setLastName("Cartman");
    }

    @Override
    public ResponseEntity<RentalDTO> createRental(@Valid RentalDTO rentalDTO) {

        RentalDTO rental = new RentalDTO();
        rental.setCar(RentalController.car);
        rental.setUser(RentalController.user);
        rental.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(rental);
    }

    @Override
    public ResponseEntity<Void> deleteRental(Integer rentalId) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RentalDTO> getRental(Integer rentalId) {
        RentalDTO rental = new RentalDTO();
        rental.setCar(car);
        rental.setUser(user);
        rental.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(rental);
    }

    @Override
    public ResponseEntity<List<RentalDTO>> getRentals(@Valid Integer userId, @Valid Integer carId, @Valid String status) {
        RentalDTO rental1 = new RentalDTO();
        rental1.setCar(car);
        rental1.setUser(user);
        rental1.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental1.setStartDateTime(OffsetDateTime.now());

        RentalDTO rental2 = new RentalDTO();
        rental2.setCar(car);
        rental2.setUser(user);
        rental2.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental2.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(List.of(rental1, rental2));
    }

    @Override
    public ResponseEntity<RentalDTO> patchRental(Integer rentalId, @Valid RentalDTO rentalDTO) {
        RentalDTO rental1 = new RentalDTO();
        rental1.setCar(car);
        rental1.setUser(user);
        rental1.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental1.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(rental1);
    }
}
