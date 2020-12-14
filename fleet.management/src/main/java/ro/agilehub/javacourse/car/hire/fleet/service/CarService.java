package ro.agilehub.javacourse.car.hire.fleet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocumentDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;

import java.util.List;

public interface CarService {

    List<CarDO> findAll();

    CarDO findById(String id);

    void deleteById(String id);

    CarDO addCar(CarDO carDO);

    CarDO updateCar(String carId, List<PatchDocumentDTO> patchDocumentDTO) throws JsonPatchException, JsonProcessingException;
}
