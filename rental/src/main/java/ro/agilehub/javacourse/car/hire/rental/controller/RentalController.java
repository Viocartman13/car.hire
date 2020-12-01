package ro.agilehub.javacourse.car.hire.rental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocumentDTO;
import ro.agilehub.javacourse.car.hire.api.model.RentalDTO;
import ro.agilehub.javacourse.car.hire.api.specification.RentalApi;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class RentalController implements RentalApi {

    @Override
    public ResponseEntity<RentalDTO> createRental(@Valid RentalDTO rentalDTO) {

        RentalDTO rental = new RentalDTO();
        rental.setCarId("1");
        rental.setUserId("1");
        rental.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(rental);
    }

    @Override
    public ResponseEntity<Void> deleteRental(String rentalId) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RentalDTO> getRental(String rentalId) {
        RentalDTO rental = new RentalDTO();
        rental.setCarId("1");
        rental.setUserId("2");
        rental.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(rental);
    }

    @Override
    public ResponseEntity<List<RentalDTO>> getRentals(@Valid String userId, @Valid String carId, @Valid String status) {
        RentalDTO rental1 = new RentalDTO();
        rental1.setCarId("1");
        rental1.setUserId("2");
        rental1.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental1.setStartDateTime(OffsetDateTime.now());

        RentalDTO rental2 = new RentalDTO();
        rental2.setCarId("2");
        rental2.setUserId("1");
        rental2.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental2.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(List.of(rental1, rental2));
    }

    @Override
    public ResponseEntity<RentalDTO> patchRental(String rentalId, @Valid List<PatchDocumentDTO> patchDocumentDTO) {
        RentalDTO rental1 = new RentalDTO();
        rental1.setCarId("1");
        rental1.setUserId("1");
        rental1.setStatus(RentalDTO.StatusEnum.ACTIVE);
        rental1.setStartDateTime(OffsetDateTime.now());

        return ResponseEntity.ok(rental1);
    }
}
