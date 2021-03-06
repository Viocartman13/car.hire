package ro.agilehub.javacourse.car.hire.fleet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.api.model.MakeDTO;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocumentDTO;
import ro.agilehub.javacourse.car.hire.api.specification.FleetApi;
import ro.agilehub.javacourse.car.hire.fleet.controller.mapper.CarMapperController;
import ro.agilehub.javacourse.car.hire.fleet.service.CarServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FleetController implements FleetApi {

    private final CarServiceImpl carService;
    private final CarMapperController carMapperController;

    public FleetController(CarServiceImpl carService, CarMapperController carMapperController) {
        this.carService = carService;
        this.carMapperController = carMapperController;
    }

    @Override
    public ResponseEntity<CarDTO> createCar(@Valid CarDTO carDTO) {
        try {
            var savedCar = carService.addCar(carMapperController.dtoToDomainObject(carDTO));
            return ResponseEntity.ok(carMapperController.domainObjectToDTO(savedCar));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteFleetCar(String carId) {
        try {
            carService.deleteById(carId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<CarDTO>> getFleet(@Valid String status) {
        try {
            return ResponseEntity.ok(carService.findAll()
                    .stream()
                    .map(carMapperController::domainObjectToDTO)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CarDTO> getFleetCar(String carId) {
        try {
            var foundCar = carService.findById(carId);
            return ResponseEntity.ok(carMapperController.domainObjectToDTO(foundCar));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<CarDTO> patchFleetCar(String carId, @Valid List<PatchDocumentDTO> patchDocumentDTO) {
        MakeDTO fordMake = new MakeDTO();
        fordMake.setId("2");
        fordMake.setName("Ford");
        fordMake.setDescription("Ford brand");

        CarDTO focus = new CarDTO();
        focus.setMake(fordMake);
        focus.setFuel("Diesel");
        focus.setId("2");

        return ResponseEntity.ok(focus);
    }

}
