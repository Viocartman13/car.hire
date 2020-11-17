package ro.agilehub.javacourse.car.hire.fleet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocumentDTO;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FleetController implements FleetApi {

    @Override
    public ResponseEntity<CarDTO> createCar(@Valid CarDTO carDTO) {
        CarDTO car = new CarDTO();
        car.setMake("BMW");
        car.setFuel("Gasoline");
        car.setId(1);

        return ResponseEntity.ok(car);
    }

    @Override
    public ResponseEntity<Void> deleteFleetCar(Integer carId) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CarDTO>> getFleet(@Valid String status) {
        CarDTO z3 = new CarDTO();
        z3.setMake("BMW");
        z3.setFuel("Gasoline");
        z3.setId(1);

        CarDTO focus = new CarDTO();
        focus.setMake("Ford");
        focus.setFuel("Diesel");
        focus.setId(2);

        return ResponseEntity.ok(List.of(z3, focus));
    }

    @Override
    public ResponseEntity<CarDTO> getFleetCar(Integer carId) {
        CarDTO z3 = new CarDTO();
        z3.setMake("BMW");
        z3.setFuel("Gasoline");
        z3.setId(1);

        return ResponseEntity.ok(z3);
    }

    @Override
    public ResponseEntity<CarDTO> patchFleetCar(Integer carId, @Valid List<PatchDocumentDTO> patchDocumentDTO) {
        CarDTO focus = new CarDTO();
        focus.setMake("Ford");
        focus.setFuel("Diesel");
        focus.setId(2);

        return ResponseEntity.ok(focus);
    }

}
