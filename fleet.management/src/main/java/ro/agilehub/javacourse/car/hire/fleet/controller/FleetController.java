package ro.agilehub.javacourse.car.hire.fleet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.MakeDTO;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocumentDTO;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FleetController implements FleetApi {

    @Override
    public ResponseEntity<CarDTO> createCar(@Valid CarDTO carDTO) {
        MakeDTO bmwMake = new MakeDTO();
        bmwMake.setId(1);
        bmwMake.setName("BMW");
        bmwMake.setDescription("BMW brand");

        CarDTO car = new CarDTO();
        car.setMake(bmwMake);
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
        MakeDTO bmwMake = new MakeDTO();
        bmwMake.setId(1);
        bmwMake.setName("BMW");
        bmwMake.setDescription("BMW brand");

        CarDTO z3 = new CarDTO();
        z3.setMake(bmwMake);
        z3.setFuel("Gasoline");
        z3.setId(1);

        MakeDTO fordMake = new MakeDTO();
        fordMake.setId(2);
        fordMake.setName("Ford");
        fordMake.setDescription("Ford brand");

        CarDTO focus = new CarDTO();
        focus.setMake(fordMake);
        focus.setFuel("Diesel");
        focus.setId(2);

        return ResponseEntity.ok(List.of(z3, focus));
    }

    @Override
    public ResponseEntity<CarDTO> getFleetCar(Integer carId) {
        MakeDTO bmwMake = new MakeDTO();
        bmwMake.setId(1);
        bmwMake.setName("BMW");
        bmwMake.setDescription("BMW brand");

        CarDTO z3 = new CarDTO();
        z3.setMake(bmwMake);
        z3.setFuel("Gasoline");
        z3.setId(1);

        return ResponseEntity.ok(z3);
    }

    @Override
    public ResponseEntity<CarDTO> patchFleetCar(Integer carId, @Valid List<PatchDocumentDTO> patchDocumentDTO) {
        MakeDTO fordMake = new MakeDTO();
        fordMake.setId(2);
        fordMake.setName("Ford");
        fordMake.setDescription("Ford brand");

        CarDTO focus = new CarDTO();
        focus.setMake(fordMake);
        focus.setFuel("Diesel");
        focus.setId(2);

        return ResponseEntity.ok(focus);
    }

}
